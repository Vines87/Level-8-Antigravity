package com.zauberfluff.core.domain.model

data class ContentItem(
    val id: String,
    val title: String,
    val isPremium: Boolean,
    val contentData: String = ""
)
