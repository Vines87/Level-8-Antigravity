package com.zauberfluff.feature.tutorial

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TutorialViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val CURRENT_STEP_KEY = "tutorial_step"
        private const val HAS_SEEN_TUTORIAL_KEY = "has_seen_tutorial"
    }

    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep.asStateFlow()

    private val _isFinished = MutableStateFlow(false)
    val isFinished: StateFlow<Boolean> = _isFinished.asStateFlow()

    val totalSteps = 4

    init {
        // Recover state
        viewModelScope.launch {
            val savedStep = savedStateHandle.get<Int>(CURRENT_STEP_KEY) ?: 0
            _currentStep.value = savedStep
        }
    }

    fun nextStep() {
        if (_currentStep.value < totalSteps - 1) {
            _currentStep.update { it + 1 }
            savedStateHandle[CURRENT_STEP_KEY] = _currentStep.value
        } else {
            finishTutorial()
        }
    }

    fun finishTutorial() {
        viewModelScope.launch {
            // Persist that tutorial is seen (would happen in a Repository in real app)
            savedStateHandle[HAS_SEEN_TUTORIAL_KEY] = true
            _isFinished.value = true
        }
    }
}
