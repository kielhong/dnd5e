package com.widehouse.dnd.challenge

import com.widehouse.dnd.dice.RollCondition.ADVANTAGE
import com.widehouse.dnd.dice.RollCondition.DISADVANTAGE
import com.widehouse.dnd.dice.RollCondition.NORMAL
import com.widehouse.dnd.dice.RollSituation

class SavingThrow(
    private val rollSituation: RollSituation,
    private val modifiers: List<Int>,
    private val difficultyClass: Int
) {
    fun result(): Boolean {
        if (rollSituation.dice.isEmpty()) {
            throw IllegalArgumentException()
        }

        val diceRolls = rollSituation.dice.map { it.roll() }
        val diceResult =
            when (rollSituation.condition) {
                NORMAL -> diceRolls.first()
                ADVANTAGE -> diceRolls.max()
                DISADVANTAGE -> diceRolls.min()
            }

        return (diceResult + modifiers.sumOf { it }) >= difficultyClass
    }
}
