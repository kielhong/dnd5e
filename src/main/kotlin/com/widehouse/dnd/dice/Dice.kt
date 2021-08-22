package com.widehouse.dnd.dice

import com.widehouse.dnd.dice.RollCondition.ADVANTAGE
import com.widehouse.dnd.dice.RollCondition.DISADVANTAGE
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class Dice {
    fun roll(die: Die, condition: RollCondition? = null) =
        when (condition) {
            ADVANTAGE -> max(roll(die.number), roll(die.number))
            DISADVANTAGE -> min(roll(die.number), roll(die.number))
            else -> roll(die.number)
        }

    private fun roll(number: Int) = Random.nextInt(1, number + 1)
}
