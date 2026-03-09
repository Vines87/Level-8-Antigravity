package com.zauberfluff.core.domain.factory

import com.zauberfluff.core.domain.model.LicenseStatus
import com.zauberfluff.core.domain.model.Symbol
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DeckFactoryTest {

    private val factory = DeckFactory()

    @Test
    fun `generateDeck free license only contains free symbols`() {
        val deck = factory.generateDeck(LicenseStatus.FREE, 20)

        assertTrue(deck.isNotEmpty())
        // Non-Joker cards must be free symbols
        assertTrue(deck.filter { !it.symbol.isJoker }.all { !it.symbol.isPremium })
    }

    @Test
    fun `generateDeck premium license contains all symbols`() {
        val deck = factory.generateDeck(LicenseStatus.PREMIUM, 100)

        assertTrue(deck.isNotEmpty())
        assertTrue(deck.any { it.symbol.isPremium })
    }

    @Test
    fun `generateDeck returns deckSize plus 4 joker cards`() {
        val deck = factory.generateDeck(LicenseStatus.FREE, 40)
        assertEquals(44, deck.size)
    }

    // ── Joker (Glitzer-Karte) tests ───────────────────────────────────────────

    @Test
    fun `generateDeck always contains exactly 4 Jokers`() {
        val deck = factory.generateDeck(LicenseStatus.FREE, 40)
        assertEquals(4, deck.count { it.symbol == Symbol.JOKER })
    }

    @Test
    fun `generateDeck Jokers appear in premium deck too`() {
        val deck = factory.generateDeck(LicenseStatus.PREMIUM, 40)
        assertEquals(4, deck.count { it.symbol == Symbol.JOKER })
    }

    @Test
    fun `generateDeck non-joker cards never have JOKER symbol`() {
        val deck = factory.generateDeck(LicenseStatus.FREE, 40)
        val jokerCount = deck.count { it.symbol == Symbol.JOKER }
        // exactly 4 jokers, all others are regular symbols
        assertEquals(4, jokerCount)
        assertEquals(40, deck.count { it.symbol != Symbol.JOKER })
    }
}

