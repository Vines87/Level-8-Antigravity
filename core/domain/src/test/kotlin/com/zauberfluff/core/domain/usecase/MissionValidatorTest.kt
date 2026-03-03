package com.zauberfluff.core.domain.usecase

import com.zauberfluff.core.domain.model.Card
import com.zauberfluff.core.domain.model.Mission
import com.zauberfluff.core.domain.model.MissionType
import com.zauberfluff.core.domain.model.Symbol
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MissionValidatorTest {

    private val validator = MissionValidator()

    @Test
    fun `validate THREE_SAME returns true for 3 exact symbols`() {
        val mission = Mission(MissionType.THREE_SAME, false, 3)
        val cards = listOf(Card(Symbol.DRAGON), Card(Symbol.DRAGON), Card(Symbol.DRAGON))
        assertTrue(validator.validate(mission, cards))
    }

    @Test
    fun `validate THREE_SAME returns false for mixed symbols`() {
        val mission = Mission(MissionType.THREE_SAME, false, 3)
        val cards = listOf(Card(Symbol.DRAGON), Card(Symbol.UNICORN), Card(Symbol.DRAGON))
        assertFalse(validator.validate(mission, cards))
    }

    @Test
    fun `validate THREE_DIFFERENT returns true for 3 distinct symbols`() {
        val mission = Mission(MissionType.THREE_DIFFERENT, false, 3)
        val cards = listOf(Card(Symbol.DRAGON), Card(Symbol.UNICORN), Card(Symbol.FAIRY))
        assertTrue(validator.validate(mission, cards))
    }
    
    @Test
    fun `validate returns false if required count is not met`() {
        val mission = Mission(MissionType.THREE_SAME, false, 3)
        val cards = listOf(Card(Symbol.DRAGON), Card(Symbol.DRAGON))
        assertFalse(validator.validate(mission, cards))
    }
}
