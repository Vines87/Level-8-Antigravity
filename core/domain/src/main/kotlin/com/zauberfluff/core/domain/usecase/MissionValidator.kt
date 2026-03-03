package com.zauberfluff.core.domain.usecase

import com.zauberfluff.core.domain.model.Card
import com.zauberfluff.core.domain.model.Mission
import com.zauberfluff.core.domain.model.MissionType

class MissionValidator {

    fun validate(mission: Mission, selectedCards: List<Card>): Boolean {
        if (selectedCards.size != mission.requiredCount) return false
        
        return when (mission.type) {
            MissionType.THREE_SAME, MissionType.FOUR_SAME -> {
                // All cards must have the same symbol
                selectedCards.map { it.symbol }.distinct().size == 1
            }
            MissionType.THREE_DIFFERENT, MissionType.FOUR_DIFFERENT -> {
                // All cards must have different symbols
                selectedCards.map { it.symbol }.distinct().size == selectedCards.size
            }
        }
    }
}
