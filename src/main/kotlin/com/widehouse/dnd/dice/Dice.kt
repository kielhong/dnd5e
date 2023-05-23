package com.widehouse.dnd.dice

import kotlin.random.Random

enum class Dice(val side: Int) {
    D100(100),
    D20(20),
    D12(12),
    D10(10),
    D8(8),
    D6(6),
    D4(4);

    fun roll(): Int =
        Random.nextInt(1, side + 1)
}
