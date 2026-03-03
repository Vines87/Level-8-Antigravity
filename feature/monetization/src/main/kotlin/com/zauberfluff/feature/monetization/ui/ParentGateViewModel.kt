package com.zauberfluff.feature.monetization.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zauberfluff.core.domain.usecase.CheckParentalGateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ParentGateViewModel @Inject constructor(
    private val checkParentalGateUseCase: CheckParentalGateUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_PARENT_GATE_STATE = "parent_gate_state_snapshot"
    }

    private val _uiState = MutableStateFlow(ParentGateUiState())
    val uiState: StateFlow<ParentGateUiState> = _uiState.asStateFlow()

    init {
        restoreStateOrGenerateNew()
        observeAndSaveState()
    }

    private fun restoreStateOrGenerateNew() {
        val savedJson = savedStateHandle.get<String>(KEY_PARENT_GATE_STATE)
        if (savedJson != null) {
            try {
                _uiState.value = Json.decodeFromString<ParentGateUiState>(savedJson)
            } catch (e: Exception) {
                generateNewQuestion()
            }
        } else {
            generateNewQuestion()
        }
    }

    private fun observeAndSaveState() {
        viewModelScope.launch {
            _uiState.collect { state ->
                savedStateHandle[KEY_PARENT_GATE_STATE] = Json.encodeToString(state)
            }
        }
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

@Serializable
data class ParentGateUiState(
    val question: String = "",
    val expectedAnswer: Int = 0,
    val currentInput: String = "",
    val isError: Boolean = false
)
