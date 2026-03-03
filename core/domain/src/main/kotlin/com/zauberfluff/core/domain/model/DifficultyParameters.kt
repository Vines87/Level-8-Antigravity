package com.zauberfluff.core.domain.model

data class DifficultyParameters(
    val aiAggressiveness: Float, // 0.0 to 1.0 representing how optimal the AI plays
    val allowsPremiumMissions: Boolean // whether AI can target complex/premium missions
)
