package com.zauberfluff.feature.monetization.ui

import androidx.lifecycle.SavedStateHandle
import com.zauberfluff.core.domain.usecase.CheckParentalGateUseCase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class ParentGateViewModelTest {

    @Test
    fun `viewModel restores state from SavedStateHandle if present`() {
        val useCase = CheckParentalGateUseCase()
        val savedState = ParentGateUiState(question = "5 + 5 = ?", expectedAnswer = 10, currentInput = "1", isError = true)
        val json = Json.encodeToString(savedState)
        
        val handle = SavedStateHandle(mapOf("parent_gate_state_snapshot" to json))
        
        // This will trigger restoreStateOrGenerateNew() inside init block
        val viewModel = ParentGateViewModel(useCase, handle)
        
        assertEquals("5 + 5 = ?", viewModel.uiState.value.question)
        assertEquals(10, viewModel.uiState.value.expectedAnswer)
        assertEquals("1", viewModel.uiState.value.currentInput)
        assertEquals(true, viewModel.uiState.value.isError)
    }

    @Test
    fun `viewModel generates new state if SavedStateHandle is empty`() {
        val useCase = CheckParentalGateUseCase()
        val handle = SavedStateHandle()
        
        val viewModel = ParentGateViewModel(useCase, handle)
        
        // Expected answer shouldn't be 0 since generator creates 5..15 additions
        assert(viewModel.uiState.value.expectedAnswer > 0) 
        assertEquals("", viewModel.uiState.value.currentInput)
        assertEquals(false, viewModel.uiState.value.isError)
    }
}
