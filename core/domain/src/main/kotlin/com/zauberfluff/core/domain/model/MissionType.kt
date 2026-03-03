package com.zauberfluff.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class MissionType {
    THREE_SAME,
    THREE_DIFFERENT,
    FOUR_SAME,
    FOUR_DIFFERENT
}
