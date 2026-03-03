package com.zauberfluff.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    val players: List<Player>,
    val currentPlayerIndex: Int = 0,
    val deck: List<Card>,
    val activeMission: Mission?,
    val isGameOver: Boolean = false,
    val winner: Player? = null
)
