package com.widehouse.dnd.challenge

import com.widehouse.dnd.dice.RollSituation

class SavingThrow(
    private val rollSituation: RollSituation,
    private val modifiers: List<Int>,
    private val difficultyClass: Int
) : Challenge {
    override fun challenge(): Boolean {
        return (rollSituation.roll() + modifiers.sumOf { it }) >= difficultyClass
    }
}
