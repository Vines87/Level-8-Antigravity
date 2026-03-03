package com.zauberfluff.feature.monetization.ui

import androidx.lifecycle.ViewModel
import com.zauberfluff.core.domain.usecase.CheckParentalGateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ParentGateViewModel @Inject constructor(
    private val checkParentalGateUseCase: CheckParentalGateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ParentGateUiState())
    val uiState: StateFlow<ParentGateUiState> = _uiState.asStateFlow()

    init {
        generateNewQuestion()
    }

    private fun generateNewQuestion() {
        val (question, answer) = checkParentalGateUseCase.generateQuestion()
        _uiState.update { 
            it.copy(
                question = question,
                expectedAnswer = answer,
                currentInput = "",
                isError = false
            )
        }
    }

    fun onInputChanged(input: String) {
        _uiState.update { it.copy(currentInput = input, isError = false) }
    }

    fun onSubmit(onSuccess: () -> Unit) {
        val currentState = _uiState.value
        val actualAnswer = currentState.currentInput.toIntOrNull()

        if (actualAnswer != null && checkParentalGateUseCase.verifyAnswer(currentState.expectedAnswer, actualAnswer)) {
            onSuccess()
        } else {
            _uiState.update { it.copy(isError = true, currentInput = "") }
        }
    }

    fun onCancel(onCancelAction: () -> Unit) {
        onCancelAction()
    }
}

data class ParentGateUiState(
    val question: String = "",
    val expectedAnswer: Int = 0,
    val currentInput: String = "",
    val isError: Boolean = false
)
