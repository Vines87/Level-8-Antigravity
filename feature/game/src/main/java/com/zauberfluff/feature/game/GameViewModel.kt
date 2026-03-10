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
import com.zauberfluff.core.domain.usecase.TurnManager
import com.zauberfluff.core.domain.usecase.ai.AiDecisionEngine
import com.zauberfluff.core.domain.usecase.ai.EasyAiStrategy
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
    private val gameEngine: GameCoreEngine = GameCoreEngine(MissionValidator(), seedEngine, deckFactory),
    private val aiDecisionEngine: AiDecisionEngine = AiDecisionEngine(gameEngine),
    private val turnManager: TurnManager = TurnManager(aiDecisionEngine),
    private val aiStrategy: EasyAiStrategy = EasyAiStrategy(seedEngine.random, MissionValidator())
) : ViewModel() {

    companion object {
        private const val KEY_GAME_STATE = "game_state_snapshot"
    }

    private val _gameState = MutableStateFlow(createInitialState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val _selectedCards = MutableStateFlow<Set<String>>(emptySet())
    val selectedCards: StateFlow<Set<String>> = _selectedCards.asStateFlow()

    /** ID der zuletzt gezogenen Karte – triggert die Karten-Flug-Animation. Null = keine Animation. */
    private val _lastDrawnCardId = MutableStateFlow<String?>(null)
    val lastDrawnCardId: StateFlow<String?> = _lastDrawnCardId.asStateFlow()

    /** Kurzes true-Signal, das das Missions-Feuerwerk (Bounce + Glitzer) auslöst. */
    private val _missionFireworkTrigger = MutableStateFlow(false)
    val missionFireworkTrigger: StateFlow<Boolean> = _missionFireworkTrigger.asStateFlow()

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
        return gameEngine.setupGame(players, LicenseStatus.PREMIUM)
    }

    fun drawCard() {
        _gameState.update { state ->
            val currentPlayer = state.players[state.currentPlayerIndex]
            
            // Anti-Deadlock: If the hand is full, drawing is blocked. Do not end the turn!
            if (currentPlayer.hand.size >= Player.MAX_HAND_SIZE) {
                return@update state
            }
            
            val handBefore = currentPlayer.hand.map { it.id }.toSet()
            val stateAfterDraw = gameEngine.drawCard(state, currentPlayer.id)

            // Detect the newly added card and trigger the fly-in animation
            val playerAfterDraw = stateAfterDraw.players.find { it.id == currentPlayer.id }
            val newCardId = playerAfterDraw?.hand?.firstOrNull { it.id !in handBefore }?.id
            if (newCardId != null) {
                _lastDrawnCardId.value = newCardId
                viewModelScope.launch {
                    kotlinx.coroutines.delay(800)
                    _lastDrawnCardId.value = null
                }
            }

            turnManager.advanceTurn(stateAfterDraw, aiStrategy)
        }
    }

    fun discardSelectedCard() {
        _gameState.update { state ->
            val currentPlayer = state.players[state.currentPlayerIndex]
            val cardsToDiscard = currentPlayer.hand.filter { it.id in _selectedCards.value }
            
            if (cardsToDiscard.isEmpty()) return@update state
            
            // Discard exactly ONE card to end the turn
            val cardToDiscard = cardsToDiscard.first()
            val currentState = gameEngine.discardCard(state, currentPlayer.id, cardToDiscard.id)
            
            _selectedCards.value = emptySet()
            
            turnManager.advanceTurn(currentState, aiStrategy)
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

            // Clear selection and trigger firework if mission was successful (score increased)
            val newPlayer = newState.players.find { it.id == currentPlayer.id }
            if (newPlayer != null && newPlayer.score > currentPlayer.score) {
                _selectedCards.value = emptySet()
                _missionFireworkTrigger.value = true
                viewModelScope.launch {
                    kotlinx.coroutines.delay(700)
                    _missionFireworkTrigger.value = false
                }
            }

            turnManager.advanceTurn(newState, aiStrategy)
        }
    }
}
