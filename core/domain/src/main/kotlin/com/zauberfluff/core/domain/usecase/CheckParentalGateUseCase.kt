package com.zauberfluff.core.domain.usecase

import javax.inject.Inject

class CheckParentalGateUseCase @Inject constructor() {
    fun generateQuestion(): Pair<String, Int> {
        val a = (5..15).random()
        val b = (5..15).random()
        return Pair("$a + $b = ?", a + b)
    }

    fun verifyAnswer(expected: Int, actual: Int): Boolean {
        return expected == actual
    }
}
