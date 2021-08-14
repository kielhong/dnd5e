package com.widehouse.dnd

import kotlin.random.Random

class Dice {
    fun roll(number: Int): Int {
        return Random.nextInt(1, number + 1)
    }
}