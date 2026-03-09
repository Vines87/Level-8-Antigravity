package com.zauberfluff.core.domain.usecase

import javax.inject.Inject

class CheckParentalGateUseCase @Inject constructor() {
    fun generateQuestion(): Pair<String, Int> {
        val a = (5..15).random()
        val b = (5..15).random()Paket 5: Das Joker-System (Glitzer-Karte)
        Ziel: Erleichterung für schwierige Runden.

        Dateien: Symbol.kt, MissionValidator.kt, DeckFactory.kt

        Anweisung:

        Füge JOKER zu Symbol hinzu.

        Passe MissionValidator.validate an: Ein JOKER zählt als Übereinstimmung für JEDE Mission.

        Mische in der DeckFactory exakt 4 Joker-Karten in das Deck ein.

        Stelle sicher, dass der Joker in der UI durch den „Regenbogen-Herz“-Vektor besonders auffällig glänzt.
        return Pair("$a + $b = ?", a + b)
    }

    fun verifyAnswer(expected: Int, actual: Int): Boolean {
        return expected == actual
    }
}
