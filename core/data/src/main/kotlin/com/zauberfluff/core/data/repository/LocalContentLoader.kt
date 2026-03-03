package com.zauberfluff.core.data.repository

import com.zauberfluff.core.domain.model.ContentItem
import com.zauberfluff.core.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LocalContentLoader @Inject constructor() : ContentRepository {

    // Mocked content for Zauberfluff
    private val allContent = listOf(
        ContentItem(id = "1", title = "Zauberwald Story", isPremium = false),
        ContentItem(id = "2", title = "Fluff's Lullaby", isPremium = false),
        ContentItem(id = "3", title = "Sternenreise", isPremium = false),
        ContentItem(id = "4", title = "Mondscheintanz", isPremium = false),
        ContentItem(id = "5", title = "Traumschloss", isPremium = true),
        ContentItem(id = "6", title = "Glitzerwolke", isPremium = true),
        ContentItem(id = "7", title = "Zauberspruch", isPremium = true),
        ContentItem(id = "8", title = "Feenstaub", isPremium = true),
        ContentItem(id = "9", title = "Drachenflug", isPremium = true),
        ContentItem(id = "10", title = "Schlaflied der Elfen", isPremium = true)
    )

    override fun getContent(): Flow<List<ContentItem>> {
        return flowOf(allContent)
    }
}
