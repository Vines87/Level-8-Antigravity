package com.zauberfluff.core.domain.factory

import com.zauberfluff.core.domain.model.Card
import com.zauberfluff.core.domain.model.LicenseStatus
import com.zauberfluff.core.domain.model.Symbol
import kotlin.random.Random

class DeckFactory(private val random: Random = Random.Default) {

    /**
     * Generates a randomized deck based on the license status.
     * Premium allows all symbols, Free restricts to a subset (4 symbols).
     */
    fun generateDeck(licenseStatus: LicenseStatus, deckSize: Int = 40): List<Card> {
        val availableSymbols = Symbol.getAvailableSymbols(licenseStatus)
        val deck = mutableListOf<Card>()

        // Fill with random symbols (Joker excluded from pool)
        repeat(deckSize) {
            deck.add(Card(symbol = availableSymbols.random(random)))
        }

        // Mix in exactly 4 Joker (Glitzer-Karte) wildcards
        repeat(4) {
            deck.add(Card(symbol = Symbol.JOKER))
        }

        deck.shuffle(random)
        return deck            // total size = deckSize + 4
    }
}
