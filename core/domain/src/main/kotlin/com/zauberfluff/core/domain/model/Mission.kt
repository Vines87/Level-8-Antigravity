package com.zauberfluff.core.domain.model

import java.util.UUID

data class Mission(
    val type: MissionType,
    val isPremium: Boolean,
    val requiredCount: Int,
    val id: String = UUID.randomUUID().toString()
) {
    companion object {
        val FREE_MISSIONS = listOf(
            Mission(type = MissionType.THREE_SAME, isPremium = false, requiredCount = 3),
            Mission(type = MissionType.THREE_DIFFERENT, isPremium = false, requiredCount = 3)
        )
        val PREMIUM_MISSIONS = listOf(
            Mission(type = MissionType.FOUR_SAME, isPremium = true, requiredCount = 4),
            Mission(type = MissionType.FOUR_DIFFERENT, isPremium = true, requiredCount = 4)
        )
        
        fun getAvailableMissions(licenseStatus: LicenseStatus): List<Mission> {
            return if (licenseStatus == LicenseStatus.PREMIUM) {
                FREE_MISSIONS + PREMIUM_MISSIONS
            } else {
                FREE_MISSIONS
            }
        }
    }
}
