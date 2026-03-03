package com.zauberfluff.core.domain.usecase.ai

import com.zauberfluff.core.domain.model.AiAction
import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.model.Player
import com.zauberfluff.core.domain.usecase.MissionValidator
import kotlin.random.Random

class EasyAiStrategy(
    private val random: Random = Random.Default,
    private val missionValidator: MissionValidator
) : AiStrategy {

    override fun determineAction(state: GameState, aiPlayer: Player): AiAction? {
        val mission = state.activeMission ?: return AiAction.DrawCard

        // Easy AI simply checks if it can complete the current mission with hand cards.
        // It does not try to optimally drop cards to build a set, it just randomly draws or completes if lucky.
        
        // Let's generate all possible subsets of required size
        if (aiPlayer.hand.size >= mission.requiredCount) {
            val possibleCombinations = generateCombinations(aiPlayer.hand, mission.requiredCount)
            for (combination in possibleCombinations) {
                if (missionValidator.validate(mission, combination)) {
                    // With a small chance, it might even miss the opportunity (randomness of EASY)
                    // Let's say 80% chance it finds it if it has it.
                    if (random.nextFloat() < 0.8f) {
                        return AiAction.CompleteMission(combination)
                    }
                }
            }
        }

        // Otherwise draw card if under limit
        if (aiPlayer.hand.size < Player.MAX_HAND_SIZE) {
            return AiAction.DrawCard
        }
        
        // If hand is full and cannot complete mission (or missed it), it has to do nothing or we need a discard mechanic.
        // The rules say "Handkartenlimit von exakt 5 Karten" and "kein Zugriff auf verdeckte Karten". 
        // We might need to assume a discard or just DrawCard and engine handles full hand by doing nothing.
        return null 
    }
    
    // Simple helper to generate combinations of size k
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
