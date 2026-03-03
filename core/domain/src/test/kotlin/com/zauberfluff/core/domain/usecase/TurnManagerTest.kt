package com.zauberfluff.core.domain.usecase

import com.zauberfluff.core.domain.model.*
import com.zauberfluff.core.domain.engine.DeterministicSeedEngine
import com.zauberfluff.core.domain.factory.DeckFactory
import com.zauberfluff.core.domain.usecase.ai.AiDecisionEngine
import com.zauberfluff.core.domain.usecase.ai.EasyAiStrategy
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class TurnManagerTest {

    private val validator = MissionValidator()
    private val seedEngine = DeterministicSeedEngine(42)
    private val deckFactory = DeckFactory(seedEngine.random)
    private val engine = GameCoreEngine(validator, seedEngine, deckFactory)
    private val aiDecisionEngine = AiDecisionEngine(engine)
    private val turnManager = TurnManager(aiDecisionEngine)
    private val strategy = EasyAiStrategy(Random(42), validator)

    @Test
    fun `advanceTurn rotates correctly between humans`() {
        val p1 = Player("p1", "Alice", isAi = false)
        val p2 = Player("p2", "Bob", isAi = false)
        val state = GameState(listOf(p1, p2), currentPlayerIndex = 0, deck = emptyList(), activeMission = null)

        val newState = turnManager.advanceTurn(state, strategy)
        assertEquals(1, newState.currentPlayerIndex)

        val newState2 = turnManager.advanceTurn(newState, strategy)
        assertEquals(0, newState2.currentPlayerIndex)
    }

    @Test
    fun `advanceTurn auto-plays AI turns until human is reached`() {
        val human = Player("h1", "Alice", isAi = false)
        val ai1 = Player("ai1", "Bot1", isAi = true)
        val ai2 = Player("ai2", "Bot2", isAi = true)
        // 3 players: Human(0), AI(1), AI(2)
        val state = GameState(listOf(human, ai1, ai2), currentPlayerIndex = 0, deck = listOf(Card(Symbol.DRAGON), Card(Symbol.DRAGON)), activeMission = null)

        // Advance from human -> should play ai1 -> should play ai2 -> return to human
        val newState = turnManager.advanceTurn(state, strategy)
        
        // At this point, the index should be 0 (back to Human), and both AIs should have had their turns (e.g. drawn cards).
        assertEquals(0, newState.currentPlayerIndex)
        assertEquals(1, newState.players[1].hand.size)
        assertEquals(1, newState.players[2].hand.size)
    }
}
