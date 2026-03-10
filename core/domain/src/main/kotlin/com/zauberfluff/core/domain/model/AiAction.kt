package com.zauberfluff.core.domain.model

sealed class AiAction {
    data object DrawCard : AiAction()
    data class DiscardCard(val card: Card) : AiAction()
    data class CompleteMission(val selectedCards: List<Card>) : AiAction()
}
