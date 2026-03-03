package com.zauberfluff.core.domain.usecase

import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.usecase.ai.AiDecisionEngine
import com.zauberfluff.core.domain.usecase.ai.AiStrategy

class TurnManager(
    private val aiDecisionEngine: AiDecisionEngine
) {

    /**
     * Advances the turn to the next player.
     * If the next player is an AI, it executes their turn automatically and advances again recursively 
     * until a human player's turn is reached or the game ends.
     */
    tailrec fun advanceTurn(gameState: GameState, aiStrategy: AiStrategy): GameState {
        if (gameState.isGameOver || gameState.players.isEmpty()) {
            return gameState
        }

        val nextIndex = (gameState.currentPlayerIndex + 1) % gameState.players.size
        val nextPlayer = gameState.players[nextIndex]

        val newState = gameState.copy(currentPlayerIndex = nextIndex)

        return if (nextPlayer.isAi) {
            val stateAfterAiTurn = aiDecisionEngine.executeAiTurn(newState, aiStrategy)
            if (stateAfterAiTurn.isGameOver) {
                stateAfterAiTurn
            } else {
                advanceTurn(stateAfterAiTurn, aiStrategy)
            }
        } else {
            newState
        }
    }
}
