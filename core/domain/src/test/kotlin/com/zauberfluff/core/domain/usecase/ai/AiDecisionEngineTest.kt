package com.zauberfluff.core.domain.usecase.ai

import com.zauberfluff.core.domain.model.*
import com.zauberfluff.core.domain.engine.DeterministicSeedEngine
import com.zauberfluff.core.domain.factory.DeckFactory
import com.zauberfluff.core.domain.usecase.GameCoreEngine
import com.zauberfluff.core.domain.usecase.MissionValidator
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class AiDecisionEngineTest {

    private val validator = MissionValidator()
    private val seedEngine = DeterministicSeedEngine(42)
    private val deckFactory = DeckFactory(seedEngine.random)
    private val engine = GameCoreEngine(validator, seedEngine, deckFactory)
    private val aiDecisionEngine = AiDecisionEngine(engine)
    private val random = Random(42)

    @Test
    fun `executeAiTurn draws card when strategy decides to draw`() {
        val player = Player("ai", "Bot", isAi = true, hand = emptyList())
        val state = GameState(listOf(player), currentPlayerIndex = 0, deck = listOf(Card(Symbol.DRAGON)), activeMission = null)
        val easyStrategy = EasyAiStrategy(random, validator)
        
        val newState = aiDecisionEngine.executeAiTurn(state, easyStrategy)
        
        assertEquals(1, newState.players.first().hand.size)
        assertEquals(0, newState.deck.size)
    }

    @Test
    fun `executeAiTurn completes mission when strategy decides to complete`() {
        val cards = listOf(Card(Symbol.DRAGON), Card(Symbol.DRAGON), Card(Symbol.DRAGON))
        val player = Player("ai", "Bot", isAi = true, hand = cards)
        val state = GameState(listOf(player), currentPlayerIndex = 0, deck = emptyList(), activeMission = Mission(MissionType.THREE_SAME, false, 3))
        
        // Use optimized strategy with 1.0 aggressiveness to guarantee completion
        val strategy = OptimizedAiStrategy(DifficultyParameters(1.0f, true), random, validator)
        
        val newState = aiDecisionEngine.executeAiTurn(state, strategy)
        
        assertEquals(1, newState.players.first().score)
        assertEquals(0, newState.players.first().hand.size)
    }
    
    @Test
    fun `executeAiTurn does nothing if player is not AI`() {
        val player = Player("p1", "Human", isAi = false, hand = emptyList())
        val state = GameState(listOf(player), currentPlayerIndex = 0, deck = listOf(Card(Symbol.DRAGON)), activeMission = null)
        val strategy = EasyAiStrategy(random, validator)
        
        val newState = aiDecisionEngine.executeAiTurn(state, strategy)
        
        assertEquals(0, newState.players.first().hand.size) // No card drawn because human
    }
}
