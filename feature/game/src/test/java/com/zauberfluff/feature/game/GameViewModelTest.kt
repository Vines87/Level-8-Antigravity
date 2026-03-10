package com.zauberfluff.feature.game

import androidx.lifecycle.SavedStateHandle
import com.zauberfluff.core.domain.engine.DeterministicSeedEngine
import com.zauberfluff.core.domain.factory.DeckFactory
import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.model.Player
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

@Ignore("Needs Coroutines Test setup for viewModelScope")
class GameViewModelTest {

    @Test
    fun `viewModel restores state from SavedStateHandle if present`() {
        val savedPlayer = Player(id = "p1", name = "Recovered_Player", score = 4)
        val savedState = GameState(players = listOf(savedPlayer), currentPlayerIndex = 0, deck = emptyList(), activeMission = null)
        val json = Json.encodeToString(savedState)
        
        val handle = SavedStateHandle(mapOf("game_state_snapshot" to json))
        
        // Ensure to pass handle to ViewModel
        val viewModel = GameViewModel(
            savedStateHandle = handle,
            seedEngine = DeterministicSeedEngine(42L),
            deckFactory = DeckFactory(DeterministicSeedEngine(42L).random)
        )
        
        // State should be exactly what was parsed from JSON
        assertEquals(1, viewModel.gameState.value.players.size)
        assertEquals("Recovered_Player", viewModel.gameState.value.players.first().name)
        assertEquals(4, viewModel.gameState.value.players.first().score)
    }
}
