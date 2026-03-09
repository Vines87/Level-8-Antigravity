package com.zauberfluff.core.domain.usecase

import com.zauberfluff.core.domain.model.*
import com.zauberfluff.core.domain.engine.DeterministicSeedEngine
import com.zauberfluff.core.domain.factory.DeckFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameCoreEngineTest {

    private val validator = MissionValidator()
    private val seedEngine = DeterministicSeedEngine(42)
    private val deckFactory = DeckFactory(seedEngine.random)
    private val engine = GameCoreEngine(validator, seedEngine, deckFactory)

    @Test
    fun `drawCard_notAboveFive stops player from drawing past 5 cards`() {
        val fullHand = List(5) { Card(Symbol.DRAGON) }
        val player = Player("p1", "Tester", hand = fullHand)
        val deck = listOf(Card(Symbol.UNICORN))
        val state = GameState(listOf(player), deck = deck, activeMission = null)

        val updatedState = engine.drawCard(state, "p1")
        
        assertEquals(5, updatedState.players.first().hand.size)
        assertEquals(1, updatedState.deck.size)
    }

    @Test
    fun `winCondition_atSixPoints declares winner when score reaches 6`() {
        val handCards = listOf(Card(Symbol.DRAGON, id="1"), Card(Symbol.DRAGON, id="2"), Card(Symbol.DRAGON, id="3"))
        val player = Player("p1", "Tester", score = 5, hand = handCards)
        val mission = Mission(MissionType.THREE_SAME, false, 3)
        val state = GameState(listOf(player), deck = emptyList(), activeMission = mission)
        
        val updatedState = engine.completeMission(state, "p1", handCards)
        
        assertEquals(6, updatedState.players.first().score)
        assertTrue(updatedState.isGameOver)
        assertEquals(updatedState.players.first(), updatedState.winner)
    }

    @Test
    fun `engine produces 100 percent reproducible states for the same seed`() {
        // Run 1
        val seed1 = DeterministicSeedEngine(42L)
        val engine1 = GameCoreEngine(validator, seed1, DeckFactory(seed1.random))
        
        val player1 = Player("p1", "Tester", score = 0, hand = emptyList())
        val state1 = GameState(listOf(player1), deck = emptyList(), activeMission = null)
        val state1AfterDraw = engine1.drawCard(state1, "p1")
        
        // Run 2
        val seed2 = DeterministicSeedEngine(42L)
        val engine2 = GameCoreEngine(validator, seed2, DeckFactory(seed2.random))
        
        val player2 = Player("p2", "Tester", score = 0, hand = emptyList()) // Using p2 name just to test state logic relies on ID, we use same ID "p1" in call
        val state2 = GameState(listOf(player1), deck = emptyList(), activeMission = null)
        val state2AfterDraw = engine2.drawCard(state2, "p1")
        
        assertEquals(
            "State 1 drawn card must exactly match State 2 drawn card for same seed",
            state1AfterDraw.players.first().hand.first().symbol,
            state2AfterDraw.players.first().hand.first().symbol
        )

        // Mission complete reproducibility
        val handCards = listOf(Card(Symbol.DRAGON, id="1"), Card(Symbol.DRAGON, id="2"), Card(Symbol.DRAGON, id="3"))
        val stateMiss1 = GameState(listOf(Player("p1", "Tester", hand = handCards)), deck = emptyList(), activeMission = Mission(MissionType.THREE_SAME, false, 3))
        val stateMiss2 = GameState(listOf(Player("p1", "Tester", hand = handCards)), deck = emptyList(), activeMission = Mission(MissionType.THREE_SAME, false, 3))

        val endState1 = engine1.completeMission(stateMiss1, "p1", handCards)
        val endState2 = engine2.completeMission(stateMiss2, "p1", handCards)

        assertEquals(
            "New missions must exactly match for same seed",
            endState1.activeMission?.type,
            endState2.activeMission?.type
        )
    }
}
