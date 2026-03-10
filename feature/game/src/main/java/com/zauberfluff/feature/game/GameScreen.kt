package com.zauberfluff.feature.game

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.createSavedStateHandle
import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.model.Mission
import com.zauberfluff.core.domain.model.MissionType
import com.zauberfluff.core.domain.model.Symbol
import com.zauberfluff.core.ui.SymbolIcon
import com.zauberfluff.core.ui.ZauberfluffAdaptiveLayout
import com.zauberfluff.core.ui.pulseClickable

// ─── Display name helpers ────────────────────────────────────────────────────

private fun MissionType.displayName(): String = when (this) {
    MissionType.THREE_SAME      -> "3 gleiche Freunde"
    MissionType.THREE_DIFFERENT -> "3 Verschiedene"
    MissionType.FOUR_SAME       -> "4 gleiche Freunde"
    MissionType.FOUR_DIFFERENT  -> "Bunte Mischung"
}

/**
 * Returns candidate symbols the current player could use to fulfil [mission].
 * For SAME missions:   up to [requiredCount] copies of the most-common symbol.
 * For DIFFERENT missions: the [requiredCount] rarest/any symbols the player has.
 */
private fun playerCandidateSymbols(
    handSymbols: List<Symbol>,
    mission: Mission
): List<Symbol> {
    return when (mission.type) {
        MissionType.THREE_SAME, MissionType.FOUR_SAME -> {
            val grouped = handSymbols.groupBy { it }
            val best = grouped.maxByOrNull { it.value.size } ?: return emptyList()
            best.value.take(mission.requiredCount)
        }
        MissionType.THREE_DIFFERENT, MissionType.FOUR_DIFFERENT -> {
            handSymbols.distinct().take(mission.requiredCount)
        }
    }
}

// ─── Screen entry point ──────────────────────────────────────────────────────

@Composable
fun GameScreen(
    windowSizeClass: WindowSizeClass,
    viewModel: GameViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                GameViewModel(savedStateHandle = createSavedStateHandle())
            }
        }
    )
) {
    val gameState by viewModel.gameState.collectAsState()
    val selectedCards by viewModel.selectedCards.collectAsState()

    ZauberfluffAdaptiveLayout(
        windowSizeClass = windowSizeClass,
        portraitLayout  = { GameLayoutPortrait(gameState, selectedCards, viewModel) },
        landscapeLayout = { GameLayoutLandscape(gameState, selectedCards, viewModel) },
        tabletLayout    = { GameLayoutTablet(gameState, selectedCards, viewModel) }
    )
}

// ─── Layout variants ─────────────────────────────────────────────────────────

@Composable
private fun GameLayoutPortrait(
    gameState: GameState,
    selectedCards: Set<String>,
    viewModel: GameViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(gameState)
        MissionArea(gameState)
        HandArea(gameState, selectedCards, viewModel)
        ActionArea(viewModel, gameState, selectedCards)
    }
}

@Composable
private fun GameLayoutLandscape(
    gameState: GameState,
    selectedCards: Set<String>,
    viewModel: GameViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(gameState)
            Spacer(modifier = Modifier.height(16.dp))
            MissionArea(gameState)
        }
        Column(
            modifier = Modifier.weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HandArea(gameState, selectedCards, viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            ActionArea(viewModel, gameState, selectedCards)
        }
    }
}

@Composable
private fun GameLayoutTablet(
    gameState: GameState,
    selectedCards: Set<String>,
    viewModel: GameViewModel
) {
    GameLayoutLandscape(gameState, selectedCards, viewModel)
}

// ─── TopBar ──────────────────────────────────────────────────────────────────

@Composable
private fun TopBar(gameState: GameState) {
    val currentPlayer = gameState.players.getOrNull(gameState.currentPlayerIndex)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "⭐ ${currentPlayer?.score ?: 0} / 6",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C3E50)
        )
        if (gameState.isGameOver) {
            Text(
                text = "🎉 Gewonnen!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF27AE60)
            )
        }
    }
}

// ─── MissionArea — Sammel-Körbe ───────────────────────────────────────────────

@Composable
private fun MissionArea(gameState: GameState) {
    val mission = gameState.activeMission
    val currentPlayer = gameState.players.getOrNull(gameState.currentPlayerIndex)
    val handSymbols = currentPlayer?.hand?.map { it.symbol } ?: emptyList()
    val candidateSymbols = if (mission != null) playerCandidateSymbols(handSymbols, mission) else emptyList()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mission title
            Text(
                text = mission?.type?.displayName() ?: "Keine Mission",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF6D4C41)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Circular slots
            val slotCount = mission?.requiredCount ?: 0
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(slotCount) { index ->
                    val symbolForSlot = candidateSymbols.getOrNull(index)
                    MissionSlot(symbol = symbolForSlot)
                }
            }
        }
    }
}

/**
 * One circular slot in the mission basket.
 * @param symbol  When non-null the player has a card for this slot → lit up.
 *                When null → dotted grey outline with dimmed silhouette.
 */
@Composable
private fun MissionSlot(symbol: Symbol?) {
    val slotSize = 64.dp

    if (symbol != null) {
        // Lit-up slot – glowing ring + coloured icon
        val pulse by rememberInfiniteTransition(label = "slotPulse").animateFloat(
            initialValue = 1.00f,
            targetValue  = 1.06f,
            animationSpec = infiniteRepeatable(
                animation  = tween(600, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "slotScale"
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(slotSize)
                .scale(pulse)
                .clip(CircleShape)
                .background(Color.White)
                .border(width = 3.dp, color = Color(0xFFFFD54F), shape = CircleShape)
        ) {
            SymbolIcon(
                symbol   = symbol,
                modifier = Modifier.size(44.dp),
                dimmed   = false
            )
        }
    } else {
        // Empty slot – dotted grey circle with invisible but sized placeholder
        androidx.compose.foundation.Canvas(
            modifier = Modifier.size(slotSize)
        ) {
            val strokeWidth = 4.dp.toPx()
            val dashLen     = 8.dp.toPx()
            val gapLen      = 6.dp.toPx()
            drawCircle(
                color = Color(0xFFBDBDBD),
                radius = size.minDimension / 2f - strokeWidth / 2f,
                style  = Stroke(
                    width      = strokeWidth,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLen, gapLen))
                )
            )
        }
    }
}

// ─── HandArea ────────────────────────────────────────────────────────────────

@Composable
private fun HandArea(
    gameState: GameState,
    selectedCards: Set<String>,
    viewModel: GameViewModel
) {
    val currentPlayer = gameState.players.getOrNull(gameState.currentPlayerIndex) ?: return

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        currentPlayer.hand.take(5).forEach { card ->
            val isSelected = selectedCards.contains(card.id)
            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 6.dp)
                    .width(86.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (isSelected) Color(0xFF81D4FA) else Color.White
                    )
                    .then(
                        if (isSelected) Modifier.border(
                            width = 4.dp,
                            color = Color(0xFF0288D1),
                            shape = RoundedCornerShape(16.dp)
                        ) else Modifier.border(
                            width = 2.dp,
                            color = Color(0xFFB0BEC5),
                            shape = RoundedCornerShape(16.dp)
                        )
                    )
                    .pulseClickable {
                        viewModel.toggleCardSelection(card.id)
                    },
                contentAlignment = Alignment.Center
            ) {
                SymbolIcon(
                    symbol   = card.symbol,
                    modifier = Modifier.size(72.dp),
                    dimmed   = false
                )
            }
        }
    }
}

// ─── ActionArea ──────────────────────────────────────────────────────────────

@Composable
private fun ActionArea(viewModel: GameViewModel, gameState: GameState, selectedCards: Set<String>) {
    val currentPlayer = gameState.players.getOrNull(gameState.currentPlayerIndex)
    val isHandFull = currentPlayer?.hand?.size == 5
    val hasSelection = selectedCards.isNotEmpty()
    
    val isDrawEnabled = !isHandFull
    val isDiscardEnabled = selectedCards.size == 1

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Draw Button
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(4.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(if (isDrawEnabled) Color(0xFF2196F3) else Color(0xFFB0BEC5))
                .padding(horizontal = 16.dp)
                .pulseClickable(enabled = isDrawEnabled) { viewModel.drawCard() }
        ) {
            Text("Ziehen", color = Color.White, fontWeight = FontWeight.Bold)
        }

        // Wegwerfen Button
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(4.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(if (isDiscardEnabled) Color(0xFFE53935) else Color(0xFFFFCDD2))
                .padding(horizontal = 16.dp)
                .pulseClickable(enabled = isDiscardEnabled) { viewModel.discardSelectedCard() }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CuteBlackHoleIcon(modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Wegwerfen", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        // Mission Complete Button
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(4.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(if (hasSelection) Color(0xFF4CAF50) else Color(0xFFC8E6C9))
                .padding(horizontal = 16.dp)
                .pulseClickable(enabled = hasSelection) { viewModel.completeMission() }
        ) {
            Text("Mission", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CuteBlackHoleIcon(modifier: Modifier = Modifier) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        val minDim = size.minDimension
        // Base dark space swirl
        drawCircle(color = Color(0xFF2C3E50), radius = minDim / 2)
        drawCircle(color = Color(0xFF34495E), radius = minDim / 2.4f)
        drawCircle(color = Color(0xFF8E44AD), radius = minDim / 3.2f)
        drawCircle(color = Color(0xFF1ABC9C), radius = minDim / 5.0f)
        drawCircle(color = Color(0xFF000000), radius = minDim / 8.0f)
        
        // Cute tiny stars surrounding the hole
        drawCircle(color = Color.White, radius = 2.dp.toPx(), center = androidx.compose.ui.geometry.Offset(size.width * 0.25f, size.height * 0.25f))
        drawCircle(color = Color.White, radius = 1.dp.toPx(), center = androidx.compose.ui.geometry.Offset(size.width * 0.8f, size.height * 0.3f))
        drawCircle(color = Color.White, radius = 1.5f.dp.toPx(), center = androidx.compose.ui.geometry.Offset(size.width * 0.7f, size.height * 0.75f))
        drawCircle(color = Color.White, radius = 0.5f.dp.toPx(), center = androidx.compose.ui.geometry.Offset(size.width * 0.3f, size.height * 0.8f))
    }
}
