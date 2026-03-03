package com.zauberfluff.core.domain.repository

import com.zauberfluff.core.domain.model.ContentItem
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getContent(): Flow<List<ContentItem>>
}
