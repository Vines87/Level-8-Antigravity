package com.zauberfluff.core.domain.model

import java.util.UUID

data class Card(
    val symbol: Symbol,
    val id: String = UUID.randomUUID().toString()
)
