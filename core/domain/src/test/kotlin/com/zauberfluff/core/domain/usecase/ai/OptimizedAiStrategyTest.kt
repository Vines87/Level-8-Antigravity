package com.zauberfluff.core.domain.usecase.ai

import com.zauberfluff.core.domain.model.*
import com.zauberfluff.core.domain.usecase.MissionValidator
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class OptimizedAiStrategyTest {

    private val validator = MissionValidator()
    private val random = Random(42)

    @Test
    fun `determineAction completes mission consistently on HARD aggressiveness`() {
        val params = DifficultyParameters(aiAggressiveness = 1.0f, allowsPremiumMissions = true)
        val strategy = OptimizedAiStrategy(params, random, validator)
        
        val cards = listOf(Card(Symbol.ASTRONAUT), Card(Symbol.ASTRONAUT), Card(Symbol.ASTRONAUT))
        val player = Player("ai", "Bot", isAi = true, hand = cards)
        val state = GameState(listOf(player), deck = emptyList(), activeMission = Mission(MissionType.THREE_SAME, false, 3))
        
        val action = strategy.determineAction(state, player)
        
        assertTrue(action is AiAction.CompleteMission)
        assertEquals(3, (action as AiAction.CompleteMission).selectedCards.size)
    }

    @Test
    fun `determineAction might draw card on MEDIUM if it misses the combination`() {
        val params = DifficultyParameters(aiAggressiveness = 0.0f, allowsPremiumMissions = false) // 0 aggressiveness to force miss
        val strategy = OptimizedAiStrategy(params, random, validator)
        
        val cards = listOf(Card(Symbol.ASTRONAUT), Card(Symbol.ASTRONAUT), Card(Symbol.ASTRONAUT))
        val player = Player("ai", "Bot", isAi = true, hand = cards)
        val state = GameState(listOf(player), deck = listOf(Card(Symbol.PLANET)), activeMission = Mission(MissionType.THREE_SAME, false, 3))
        
        val action = strategy.determineAction(state, player)
        
        assertTrue(action is AiAction.DrawCard) // Misses completion due to 0 aggressiveness
    }
}
