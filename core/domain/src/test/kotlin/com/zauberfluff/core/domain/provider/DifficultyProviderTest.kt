package com.zauberfluff.core.domain.provider

import com.zauberfluff.core.domain.model.Difficulty
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DifficultyProviderTest {

    private val provider = DifficultyProvider()

    @Test
    fun `getParameters for EASY returns lowest aggressiveness and no premium missions`() {
        val params = provider.getParameters(Difficulty.EASY)
        assertEquals(0.2f, params.aiAggressiveness)
        assertFalse(params.allowsPremiumMissions)
    }

    @Test
    fun `getParameters for HARD returns highest aggressiveness and premium missions`() {
        val params = provider.getParameters(Difficulty.HARD)
        assertEquals(1.0f, params.aiAggressiveness)
        assertTrue(params.allowsPremiumMissions)
    }
}
