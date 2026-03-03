package com.zauberfluff.feature.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zauberfluff.core.domain.model.Card
import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.model.Mission
import com.zauberfluff.core.domain.model.Player
import com.zauberfluff.core.ui.ZauberfluffAdaptiveLayout
import com.zauberfluff.core.ui.pulseClickable
import com.zauberfluff.core.domain.model.MissionType

@Composable
fun GameScreen(
    windowSizeClass: WindowSizeClass,
    viewModel: GameViewModel = viewModel()
) {
    val gameState by viewModel.gameState.collectAsState()
    val selectedCards by viewModel.selectedCards.collectAsState()

    ZauberfluffAdaptiveLayout(
        windowSizeClass = windowSizeClass,
        portraitLayout = { GameLayoutPortrait(gameState, selectedCards, viewModel) },
        landscapeLayout = { GameLayoutLandscape(gameState, selectedCards, viewModel) },
        tabletLayout = { GameLayoutTablet(gameState, selectedCards, viewModel) }
    )
}

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
        MissionArea(gameState.activeMission)
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
            MissionArea(gameState.activeMission)
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
    // Similar to landscape but with larger elements for kids usability
    GameLayoutLandscape(gameState, selectedCards, viewModel)
}

@Composable
private fun TopBar(gameState: GameState) {
    val currentPlayer = gameState.players.getOrNull(gameState.currentPlayerIndex)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Score: ${currentPlayer?.score ?: 0}/6",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C3E50)
        )
        if (gameState.isGameOver) {
            Text(
                text = " Gewonnen!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF27AE60)
            )
        }
    }
}

@Composable
private fun MissionArea(mission: Mission?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4))
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mission",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mission?.type?.name ?: "Keine",
                fontSize = 18.sp
            )
            Text(
                text = "Sammle ${mission?.requiredCount ?: 0}",
                fontSize = 16.sp
            )
        }
    }
}

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
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) Color(0xFF81D4FA) else Color.White)
                    .pulseClickable {
                        viewModel.toggleCardSelection(card.id)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = card.symbol.name.take(2),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

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
