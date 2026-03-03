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
        
        repeat(deckSize) {
            val randomSymbol = availableSymbols.random(random)
            deck.add(Card(symbol = randomSymbol))
        }
        
        deck.shuffle(random)
        return deck
    }
}
