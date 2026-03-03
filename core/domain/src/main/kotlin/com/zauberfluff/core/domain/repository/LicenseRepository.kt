package com.zauberfluff.core.domain.repository

import com.zauberfluff.core.domain.model.LicenseStatus
import kotlinx.coroutines.flow.Flow

interface LicenseRepository {
    val licenseStatus: Flow<LicenseStatus>
    suspend fun updateLicenseStatus(status: LicenseStatus)
}
