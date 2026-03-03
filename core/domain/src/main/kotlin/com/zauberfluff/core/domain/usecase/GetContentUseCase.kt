package com.zauberfluff.core.domain.usecase

import com.zauberfluff.core.domain.model.ContentItem
import com.zauberfluff.core.domain.model.LicenseStatus
import com.zauberfluff.core.domain.repository.ContentRepository
import com.zauberfluff.core.domain.repository.LicenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository,
    private val licenseRepository: LicenseRepository
) {
    operator fun invoke(): Flow<List<ContentItem>> {
        return combine(
            contentRepository.getContent(),
            licenseRepository.licenseStatus
        ) { content, status ->
            if (status == LicenseStatus.PREMIUM) {
                content
            } else {
                // Free user: only show 40% of the available content
                val limit = (content.size * 0.4).toInt()
                content.take(limit)
            }
        }
    }
}
