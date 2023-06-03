package com.widehouse.dnd.challenge

import com.widehouse.dnd.dice.RollCondition
import com.widehouse.dnd.dice.RollSituation

class AbilityCheck(private val rollSituation: RollSituation, private val modifiers: List<Int>, private val difficultyClass: Int) {
    fun result(): Boolean {
        require(rollSituation.dice.isNotEmpty())

        val diceRolls = rollSituation.dice.map { it.roll() }
        val diceResult =
            when (rollSituation.condition) {
                RollCondition.NORMAL -> diceRolls.first()
                RollCondition.ADVANTAGE -> diceRolls.max()
                RollCondition.DISADVANTAGE -> diceRolls.min()
            }

        return (diceResult + modifiers.sumOf { it }) >= difficultyClass
    }
}
