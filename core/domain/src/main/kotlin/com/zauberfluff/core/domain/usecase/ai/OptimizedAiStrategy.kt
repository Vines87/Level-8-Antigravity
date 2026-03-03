package com.zauberfluff.core.domain.usecase.ai

import com.zauberfluff.core.domain.model.*
import com.zauberfluff.core.domain.usecase.MissionValidator
import kotlin.random.Random

class OptimizedAiStrategy(
    private val parameters: DifficultyParameters,
    private val random: Random = Random.Default,
    private val missionValidator: MissionValidator
) : AiStrategy {

    override fun determineAction(state: GameState, aiPlayer: Player): AiAction? {
        val mission = state.activeMission ?: return AiAction.DrawCard

        // If the mission is premium and this difficulty does not allow premium missions, 
        // the AI might just skip completing it (simulate struggling with complex tasks).
        // However, usually they just play less optimally. Let's say if it doesn't allow premium
        // and it's a premium mission, it has a heavily reduced chance of finding the combo.
        
        val canCompletePremium = parameters.allowsPremiumMissions || !mission.isPremium
        
        if (aiPlayer.hand.size >= mission.requiredCount) {
            val possibleCombinations = generateCombinations(aiPlayer.hand, mission.requiredCount)
            for (combination in possibleCombinations) {
                if (missionValidator.validate(mission, combination)) {
                    
                    // The chance of finding the correct combination scales with aiAggressiveness (0.0 to 1.0)
                    // If it's a premium mission and not allowed, aggressiveness drops to 0.1
                    val effectiveAggressiveness = if (!canCompletePremium && mission.isPremium) {
                        0.1f
                    } else {
                        parameters.aiAggressiveness
                    }
                    
                    if (random.nextFloat() <= effectiveAggressiveness) {
                        return AiAction.CompleteMission(combination)
                    }
                }
            }
        }

        if (aiPlayer.hand.size < Player.MAX_HAND_SIZE) {
            return AiAction.DrawCard
        }
        
        return null
    }

    private fun <T> generateCombinations(list: List<T>, k: Int): List<List<T>> {
        if (k == 0) return listOf(emptyList())
        if (list.isEmpty()) return emptyList()
        val head = list.first()
        val tail = list.drop(1)
        val withHead = generateCombinations(tail, k - 1).map { it + head }
        val withoutHead = generateCombinations(tail, k)
        return withHead + withoutHead
    }
}
