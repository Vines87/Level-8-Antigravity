package com.zauberfluff.core.domain.usecase.ai

import com.zauberfluff.core.domain.model.*
import com.zauberfluff.core.domain.usecase.MissionValidator
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class EasyAiStrategyTest {

    private val validator = MissionValidator()
    private val random = Random(42) // Fixed seed for 100% determinism (FC-H3)
    private val strategy = EasyAiStrategy(random, validator)

    @Test
    fun `determineAction draws card if mission cannot be completed`() {
        val player = Player("ai", "Bot", isAi = true, hand = listOf(Card(Symbol.ASTRONAUT)))
        val state = GameState(listOf(player), deck = listOf(Card(Symbol.PLANET)), activeMission = Mission(MissionType.THREE_SAME, false, 3))
        
        val action = strategy.determineAction(state, player)
        
        assertTrue(action is AiAction.DrawCard)
    }

    @Test
    fun `determineAction completes mission if cards are right`() {
        // Random(42).nextFloat() is 0.6559, which is < 0.8f. So it will find the mission.
        val cards = listOf(Card(Symbol.ASTRONAUT), Card(Symbol.ASTRONAUT), Card(Symbol.ASTRONAUT))
        val player = Player("ai", "Bot", isAi = true, hand = cards)
        val state = GameState(listOf(player), deck = emptyList(), activeMission = Mission(MissionType.THREE_SAME, false, 3))
        
        val action = strategy.determineAction(state, player)
        
        assertTrue(action is AiAction.CompleteMission)
        assertEquals(3, (action as AiAction.CompleteMission).selectedCards.size)
    }
}
