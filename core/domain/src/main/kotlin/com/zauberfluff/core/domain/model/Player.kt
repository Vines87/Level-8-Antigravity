package com.zauberfluff.core.domain.model

import java.util.UUID
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val isAi: Boolean = false,
    val hand: List<Card> = emptyList(),
    val score: Int = 0
) {
    companion object {
        const val MAX_HAND_SIZE = 5
        const val WINNING_SCORE = 6
    }
}
