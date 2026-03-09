package com.zauberfluff.core.domain.usecase

import com.zauberfluff.core.domain.model.Card
import com.zauberfluff.core.domain.model.Mission
import com.zauberfluff.core.domain.model.MissionType

class MissionValidator {

    fun validate(mission: Mission, selectedCards: List<Card>): Boolean {
        if (selectedCards.size != mission.requiredCount) return false

        val jokers = selectedCards.filter { it.symbol.isJoker }
        val nonJokers = selectedCards.filter { !it.symbol.isJoker }

        return when (mission.type) {
            MissionType.THREE_SAME, MissionType.FOUR_SAME -> {
                // All non-joker cards must share the same symbol;
                // jokers act as wild copies of that symbol.
                nonJokers.map { it.symbol }.distinct().size <= 1
            }
            MissionType.THREE_DIFFERENT, MissionType.FOUR_DIFFERENT -> {
                // All non-joker cards must be distinct;
                // each joker acts as a unique symbol not already present.
                val distinctNonJokers = nonJokers.map { it.symbol }.distinct()
                distinctNonJokers.size == nonJokers.size
            }
        }
    }
}
