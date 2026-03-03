package com.zauberfluff.core.domain.repository

interface ComplianceRepository {
    suspend fun auditCompliance(): Boolean
}
