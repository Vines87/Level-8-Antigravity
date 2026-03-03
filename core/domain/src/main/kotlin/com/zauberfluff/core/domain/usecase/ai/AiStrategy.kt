package com.zauberfluff.core.domain.usecase.ai

import com.zauberfluff.core.domain.model.AiAction
import com.zauberfluff.core.domain.model.GameState
import com.zauberfluff.core.domain.model.Player

interface AiStrategy {
    fun determineAction(state: GameState, aiPlayer: Player): AiAction?
}
