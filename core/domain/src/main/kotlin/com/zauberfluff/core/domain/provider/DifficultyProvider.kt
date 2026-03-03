package com.zauberfluff.core.domain.provider

import com.zauberfluff.core.domain.model.Difficulty
import com.zauberfluff.core.domain.model.DifficultyParameters

class DifficultyProvider {

    fun getParameters(difficulty: Difficulty): DifficultyParameters {
        return when (difficulty) {
            Difficulty.EASY -> DifficultyParameters(
                aiAggressiveness = 0.2f,
                allowsPremiumMissions = false
            )
            Difficulty.MEDIUM -> DifficultyParameters(
                aiAggressiveness = 0.6f,
                allowsPremiumMissions = false
            )
            Difficulty.HARD -> DifficultyParameters(
                aiAggressiveness = 1.0f,
                allowsPremiumMissions = true
            )
        }
    }
}
