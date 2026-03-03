package com.zauberfluff.core.domain.service

import kotlinx.coroutines.flow.Flow

interface BillingService {
    val isPremiumPurchased: Flow<Boolean>
    suspend fun purchasePremium()
}
