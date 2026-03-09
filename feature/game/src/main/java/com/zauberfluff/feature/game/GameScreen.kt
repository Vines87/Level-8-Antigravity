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
    viewModel: GameViewModel = viewModel()
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
        ActionArea(viewModel)
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
            ActionArea(viewModel)
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
        currentPlayer.hand.forEach { card ->
            val isSelected = selectedCards.contains(card.id)
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(60.dp, 90.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (isSelected) Color(0xFF81D4FA) else Color.White
                    )
                    .then(
                        if (isSelected) Modifier.border(
                            width = 2.5.dp,
                            color = Color(0xFF0288D1),
                            shape = RoundedCornerShape(12.dp)
                        ) else Modifier
                    )
                    .pulseClickable {
                        viewModel.toggleCardSelection(card.id)
                    },
                contentAlignment = Alignment.Center
            ) {
                SymbolIcon(
                    symbol   = card.symbol,
                    modifier = Modifier.size(44.dp),
                    dimmed   = false
                )
            }
        }
    }
}

// ─── ActionArea ──────────────────────────────────────────────────────────────

@Composable
private fun ActionArea(viewModel: GameViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF2196F3))
                .padding(horizontal = 24.dp)
                .pulseClickable { viewModel.drawCard() }
        ) {
            Text("Karte ziehen", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF4CAF50))
                .padding(horizontal = 24.dp)
                .pulseClickable { viewModel.completeMission() }
        ) {
            Text("Mission erfüllen", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
