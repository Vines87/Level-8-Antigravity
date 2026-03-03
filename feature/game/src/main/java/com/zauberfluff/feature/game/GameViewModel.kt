package com.zauberfluff.feature.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zauberfluff.core.domain.engine.DeterministicSeedEngine
import com.zauberfluff.core.domain.factory.DeckFactory
import com.zauberfluff.core.domain.model.Card
import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.model.LicenseStatus
import com.zauberfluff.core.domain.model.Mission
import com.zauberfluff.core.domain.model.Player
import com.zauberfluff.core.domain.usecase.GameCoreEngine
import com.zauberfluff.core.domain.usecase.MissionValidator
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val seedEngine: DeterministicSeedEngine = DeterministicSeedEngine(System.currentTimeMillis()), // Using current time for default app run, could be fixed seed for tests
    private val deckFactory: DeckFactory = DeckFactory(seedEngine.random),
    private val gameEngine: GameCoreEngine = GameCoreEngine(MissionValidator(), seedEngine, deckFactory)
) : ViewModel() {

    companion object {
        private const val KEY_GAME_STATE = "game_state_snapshot"
    }

    private val _gameState = MutableStateFlow(createInitialState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val _selectedCards = MutableStateFlow<Set<String>>(emptySet())
    val selectedCards: StateFlow<Set<String>> = _selectedCards.asStateFlow()

    init {
        restoreStateOrInitialize()
        observeAndSaveState()
    }

    private fun restoreStateOrInitialize() {
        val savedJson = savedStateHandle.get<String>(KEY_GAME_STATE)
        if (savedJson != null) {
            try {
                _gameState.value = Json.decodeFromString<GameState>(savedJson)
            } catch (e: Exception) {
                // If parsing fails for any reason, use initial state fallback
            }
        }
    }

    private fun observeAndSaveState() {
        viewModelScope.launch {
            _gameState.collect { state ->
                savedStateHandle[KEY_GAME_STATE] = Json.encodeToString(state)
            }
        }
    }

    private fun createInitialState(): GameState {
        val players = listOf(Player(name = "Du"), Player(name = "Zauberfluff", isAi = true))
        return GameState(
            players = players,
            deck = deckFactory.generateDeck(LicenseStatus.PREMIUM),
            activeMission = Mission.getAvailableMissions(LicenseStatus.PREMIUM).first()
        )
    }

    fun drawCard() {
        _gameState.update { state ->
            val currentPlayer = state.players[state.currentPlayerIndex]
            gameEngine.drawCard(state, currentPlayer.id)
        }
    }

    fun toggleCardSelection(cardId: String) {
        _selectedCards.update { currentSelection ->
            if (currentSelection.contains(cardId)) {
                currentSelection - cardId
            } else {
                currentSelection + cardId
            }
        }
    }

    fun completeMission() {
        _gameState.update { state ->
            val currentPlayer = state.players[state.currentPlayerIndex]
            val cardsToSubmit = currentPlayer.hand.filter { it.id in _selectedCards.value }
            
            val newState = gameEngine.completeMission(state, currentPlayer.id, cardsToSubmit)
            
            // Clear selection if mission was successful (score increased)
            val newPlayer = newState.players.find { it.id == currentPlayer.id }
            if (newPlayer != null && newPlayer.score > currentPlayer.score) {
                _selectedCards.value = emptySet()
            }
            
            newState
        }
    }
}
