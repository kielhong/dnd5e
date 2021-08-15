package com.widehouse.dnd.dice

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class Dice {
    fun roll(number: Int): Int {
        val result = Random.nextInt(1, number + 1)
        println(result)
        return result
    }

    fun roll(number: Int, rollCondition: RollCondition): Int {
        return when (rollCondition) {
            RollCondition.ADVANTAGE -> max(roll(number), roll(number))
            RollCondition.DISADVANTAGE -> min(roll(number), roll(number))
        }
    }
}
