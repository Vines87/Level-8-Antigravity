package com.zauberfluff.core.domain.factory

import com.zauberfluff.core.domain.model.LicenseStatus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DeckFactoryTest {

    private val factory = DeckFactory()

    @Test
    fun `generateDeck free license only contains free symbols`() {
        val deck = factory.generateDeck(LicenseStatus.FREE, 20)
        
        assertTrue(deck.isNotEmpty())
        assertTrue(deck.all { !it.symbol.isPremium })
    }

    @Test
    fun `generateDeck premium license contains all symbols`() {
        val deck = factory.generateDeck(LicenseStatus.PREMIUM, 100)
        
        assertTrue(deck.isNotEmpty())
        assertTrue(deck.any { it.symbol.isPremium })
    }
    
    @Test
    fun `generateDeck returns correct amount of cards`() {
        val deck = factory.generateDeck(LicenseStatus.FREE, 40)
        assertEquals(40, deck.size)
    }
}
