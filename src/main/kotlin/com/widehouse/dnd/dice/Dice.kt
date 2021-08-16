package com.widehouse.dnd.dice

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class Dice {
    fun roll(die: Die): Int {
        return Random.nextInt(1, die.number + 1)
    }

    fun roll(die: Die, rollCondition: RollCondition): Int {
        return when (rollCondition) {
            RollCondition.ADVANTAGE -> max(roll(die), roll(die))
            RollCondition.DISADVANTAGE -> min(roll(die), roll(die))
        }
    }
}
