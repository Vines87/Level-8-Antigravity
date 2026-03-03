package com.zauberfluff.core.domain.usecase.ai

import com.zauberfluff.core.domain.model.AiAction
import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.usecase.GameCoreEngine

class AiDecisionEngine(
    private val gameCoreEngine: GameCoreEngine
) {

    /**
     * Executes the AI turn based on the selected strategy.
     */
    fun executeAiTurn(gameState: GameState, strategy: AiStrategy): GameState {
        val aiPlayer = gameState.players.getOrNull(gameState.currentPlayerIndex) ?: return gameState
        if (!aiPlayer.isAi) return gameState
        if (gameState.isGameOver) return gameState

        return when (val action = strategy.determineAction(gameState, aiPlayer)) {
            is AiAction.DrawCard -> {
                gameCoreEngine.drawCard(gameState, aiPlayer.id)
            }
            is AiAction.CompleteMission -> {
                gameCoreEngine.completeMission(gameState, aiPlayer.id, action.selectedCards)
            }
            null -> {
                // If AI can't do anything (full hand, no valid mission), just return game state.
                gameState
            }
        }
    }
}
