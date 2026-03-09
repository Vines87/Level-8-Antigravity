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

    // ── Joker (Glitzer-Karte) wildcard tests ─────────────────────────────────

    @Test
    fun `validate THREE_SAME with 1 Joker counts as wildcard`() {
        val mission = Mission(MissionType.THREE_SAME, false, 3)
        val cards = listOf(Card(Symbol.DRAGON), Card(Symbol.DRAGON), Card(Symbol.JOKER))
        assertTrue(validator.validate(mission, cards))
    }

    @Test
    fun `validate THREE_SAME with 2 Jokers counts as wildcard`() {
        val mission = Mission(MissionType.THREE_SAME, false, 3)
        val cards = listOf(Card(Symbol.UNICORN), Card(Symbol.JOKER), Card(Symbol.JOKER))
        assertTrue(validator.validate(mission, cards))
    }

    @Test
    fun `validate FOUR_SAME with 4 Jokers is valid`() {
        val mission = Mission(MissionType.FOUR_SAME, false, 4)
        val cards = listOf(Card(Symbol.JOKER), Card(Symbol.JOKER), Card(Symbol.JOKER), Card(Symbol.JOKER))
        assertTrue(validator.validate(mission, cards))
    }

    @Test
    fun `validate THREE_DIFFERENT with 1 Joker counts as unique wildcard`() {
        val mission = Mission(MissionType.THREE_DIFFERENT, false, 3)
        val cards = listOf(Card(Symbol.DRAGON), Card(Symbol.UNICORN), Card(Symbol.JOKER))
        assertTrue(validator.validate(mission, cards))
    }

    @Test
    fun `validate THREE_DIFFERENT Joker does not help if non-jokers are duplicates`() {
        val mission = Mission(MissionType.THREE_DIFFERENT, false, 3)
        val cards = listOf(Card(Symbol.DRAGON), Card(Symbol.DRAGON), Card(Symbol.JOKER))
        assertFalse(validator.validate(mission, cards))
    }
}

