package com.zauberfluff.core.domain.usecase

import com.zauberfluff.core.domain.engine.DeterministicSeedEngine
import com.zauberfluff.core.domain.factory.DeckFactory
import com.zauberfluff.core.domain.model.Card
import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.model.LicenseStatus
import com.zauberfluff.core.domain.model.Mission
import com.zauberfluff.core.domain.model.Player

class GameCoreEngine(
    private val missionValidator: MissionValidator,
    private val seedEngine: DeterministicSeedEngine,
    private val deckFactory: DeckFactory
) {
    fun setupGame(players: List<Player>, licenseStatus: LicenseStatus = LicenseStatus.PREMIUM): GameState {
        val initialState = GameState(
            players = players,
            deck = emptyList(),
            activeMission = null
        )
        return startNewRound(initialState, licenseStatus)
    }

    fun drawCard(gameState: GameState, playerId: String, licenseStatus: LicenseStatus = LicenseStatus.PREMIUM): GameState {
        val player = gameState.players.find { it.id == playerId } ?: return gameState
        // Handkartenlimit von exakt 6 Karten – bei vollem Limit wird die Aktion still abgebrochen
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

        // Nur die für die Mission ausgewählten Karten werden entfernt – alle anderen bleiben erhalten
        val newHand = player.hand.filterNot { it.id in expectedIds }
        val newScore = player.score + 1
        
        val updatedPlayer = player.copy(hand = newHand, score = newScore)
        val updatedPlayers = gameState.players.map { if (it.id == playerId) updatedPlayer else it }
        
        // Siegbedingung bei Erreichen von 6 Punkten
        val isWin = newScore >= Player.WINNING_SCORE
        
        return if (!isWin) {
            // Wenn das Spiel nicht vorbei ist, starten wir eine neue Runde
            startNewRound(
                gameState.copy(players = updatedPlayers), 
                licenseStatus
            )
        } else {
            gameState.copy(
                players = updatedPlayers,
                isGameOver = true,
                winner = updatedPlayer,
                activeMission = mission
            )
        }
    }

    fun startNewRound(gameState: GameState, licenseStatus: LicenseStatus): GameState {
        // Deck neu generieren
        val newDeck = deckFactory.generateDeck(licenseStatus).shuffled(seedEngine.random).toMutableList()

        // Bestehende Karten bleiben – Hand wird nur bis auf MAX_HAND_SIZE (6) aufgefüllt
        val updatedPlayers = gameState.players.map { player ->
            val cardsNeeded = Player.MAX_HAND_SIZE - player.hand.size
            val drawnCards = mutableListOf<Card>()
            repeat(cardsNeeded) {
                if (newDeck.isNotEmpty()) {
                    drawnCards.add(newDeck.removeAt(0))
                }
            }
            player.copy(hand = player.hand + drawnCards)
        }

        // Neue Mission wählen
        val availableMissions = Mission.getAvailableMissions(licenseStatus)
        val newMission = if (availableMissions.isNotEmpty()) {
            availableMissions.random(seedEngine.random)
        } else {
            null
        }

        return gameState.copy(
            players = updatedPlayers,
            deck = newDeck,
            activeMission = newMission
        )
    }
}
