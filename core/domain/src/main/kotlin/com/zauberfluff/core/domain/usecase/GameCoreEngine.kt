package com.zauberfluff.core.domain.usecase

import com.zauberfluff.core.domain.model.Card
import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.model.Player

class GameCoreEngine(
    private val missionValidator: MissionValidator,
    private val seedEngine: DeterministicSeedEngine,
    private val deckFactory: DeckFactory
) {

    fun drawCard(gameState: GameState, playerId: String, licenseStatus: LicenseStatus = LicenseStatus.PREMIUM): GameState {
        val player = gameState.players.find { it.id == playerId } ?: return gameState
        // Handkartenlimit von exakt 5 Karten
        if (player.hand.size >= Player.MAX_HAND_SIZE) {
            return gameState
        }
        
        // Deterministische Logik für das Ziehen von Karten
        val currentDeck = if (gameState.deck.isEmpty()) {
            // Wenn das Deck leer ist, generieren wir ein neues deterministisch über die SeedEngine
            deckFactory.generateDeck(licenseStatus).shuffled(seedEngine.random)
        } else {
            gameState.deck
        }
        
        if (currentDeck.isEmpty()) return gameState // Fallback falls DeckFactory leer liefert

        // Ziehe eine zufällige Karte deterministisch aus dem Deck
        val drawnCard = currentDeck.random(seedEngine.random)
        val newDeck = currentDeck.filter { it.id != drawnCard.id }
        val newHand = player.hand + drawnCard
        
        val updatedPlayer = player.copy(hand = newHand)
        val updatedPlayers = gameState.players.map { if (it.id == playerId) updatedPlayer else it }
        
        return gameState.copy(
            players = updatedPlayers,
            deck = newDeck
        )
    }

    fun completeMission(gameState: GameState, playerId: String, selectedCards: List<Card>, licenseStatus: LicenseStatus = LicenseStatus.PREMIUM): GameState {
        val player = gameState.players.find { it.id == playerId } ?: return gameState
        val mission = gameState.activeMission ?: return gameState
        
        val expectedIds = selectedCards.map { it.id }.toSet()
        val handIds = player.hand.map { it.id }.toSet()
        if (!handIds.containsAll(expectedIds)) {
            return gameState // Player does not have all selected cards
        }

        if (!missionValidator.validate(mission, selectedCards)) {
            return gameState
        }

        val newHand = player.hand.filterNot { it.id in expectedIds }
        val newScore = player.score + 1
        
        val updatedPlayer = player.copy(hand = newHand, score = newScore)
        val updatedPlayers = gameState.players.map { if (it.id == playerId) updatedPlayer else it }
        
        // Siegbedingung bei Erreichen von 6 Punkten
        val isWin = newScore >= Player.WINNING_SCORE
        
        // Deterministische Logik für die Missionsauswahl
        val availableMissions = Mission.getAvailableMissions(licenseStatus)
        val newMission = if (availableMissions.isNotEmpty()) availableMissions.random(seedEngine.random) else null
        
        return gameState.copy(
            players = updatedPlayers,
            isGameOver = isWin,
            winner = if (isWin) updatedPlayer else null,
            activeMission = if (!isWin) newMission else mission
        )
    }
}
