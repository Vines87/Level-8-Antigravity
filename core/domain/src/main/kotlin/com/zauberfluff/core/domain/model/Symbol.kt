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
    PEGASUS(true),

    // Special – always available, injected by DeckFactory only
    JOKER(false);

    /** True only for the wildcard Joker symbol. */
    val isJoker: Boolean get() = this == JOKER

    companion object {
        /** Normal symbols used for random card generation – excludes JOKER. */
        val FREE_SYMBOLS = entries.filter { !it.isPremium && !it.isJoker }
        /** All non-Joker symbols. */
        val ALL_SYMBOLS = entries.filter { !it.isJoker }

        fun getAvailableSymbols(licenseStatus: LicenseStatus): List<Symbol> {
            return if (licenseStatus == LicenseStatus.PREMIUM) ALL_SYMBOLS else FREE_SYMBOLS
        }
    }
}
