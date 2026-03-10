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
        
        // If hand is full and cannot complete mission (or missed it), we discard a card to make room.
        if (aiPlayer.hand.isNotEmpty()) {
            val cardToDiscard = determineCardToDiscard(aiPlayer.hand, mission) ?: aiPlayer.hand.random(random)
            return AiAction.DiscardCard(cardToDiscard)
        }
        
        return null 
    }
    
    private fun determineCardToDiscard(hand: List<com.zauberfluff.core.domain.model.Card>, mission: com.zauberfluff.core.domain.model.Mission): com.zauberfluff.core.domain.model.Card? {
        if (hand.isEmpty()) return null
        
        return when (mission.type) {
            com.zauberfluff.core.domain.model.MissionType.THREE_SAME, 
            com.zauberfluff.core.domain.model.MissionType.FOUR_SAME -> {
                // Find symbol we have the most of (or JOKER)
                val symbolCounts = hand.groupBy { it.symbol }.mapValues { it.value.size }
                val bestSymbol = symbolCounts.maxByOrNull { it.value }?.key
                
                // Discard a card that is not the best symbol and not a JOKER
                val candidates = hand.filter { it.symbol != bestSymbol && it.symbol != com.zauberfluff.core.domain.model.Symbol.JOKER }
                if (candidates.isNotEmpty()) {
                    candidates.random(random)
                } else {
                    hand.firstOrNull { it.symbol != bestSymbol }
                }
            }
            com.zauberfluff.core.domain.model.MissionType.THREE_DIFFERENT, 
            com.zauberfluff.core.domain.model.MissionType.FOUR_DIFFERENT -> {
                // Find duplicate symbols (not counting JOKER)
                val symbolCounts = hand.filter { it.symbol != com.zauberfluff.core.domain.model.Symbol.JOKER }.groupBy { it.symbol }
                val duplicates = symbolCounts.values.filter { it.size > 1 }.flatten()
                
                // Discard one of the duplicates, or just a non-JOKER card if no duplicates
                if (duplicates.isNotEmpty()) {
                    duplicates.random(random)
                } else {
                    hand.firstOrNull { it.symbol != com.zauberfluff.core.domain.model.Symbol.JOKER }
                }
            }
        }
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
