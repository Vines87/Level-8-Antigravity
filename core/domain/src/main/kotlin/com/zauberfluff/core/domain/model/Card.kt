package com.zauberfluff.core.domain.model

import java.util.UUID
import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val symbol: Symbol,
    val id: String = UUID.randomUUID().toString()
)
