package com.zauberfluff.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class Symbol(val isPremium: Boolean) {
    // Free pool
    DRAGON(false),
    UNICORN(false),
    FAIRY(false),
    GOBLIN(false),
    
    // Premium pool
    PHOENIX(true),
    MERMAID(true),
    YETI(true),
    KRAKEN(true),
    GRIFFIN(true),
    PEGASUS(true);
    
    companion object {
        val FREE_SYMBOLS = entries.filter { !it.isPremium }
        val ALL_SYMBOLS = entries.toList()
        
        fun getAvailableSymbols(licenseStatus: LicenseStatus): List<Symbol> {
            return if (licenseStatus == LicenseStatus.PREMIUM) ALL_SYMBOLS else FREE_SYMBOLS
        }
    }
}
