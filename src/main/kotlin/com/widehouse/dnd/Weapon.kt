package com.widehouse.dnd

import com.widehouse.dnd.dice.Dice

class Weapon(val name: String, private val damage: List<Int>, val itemType: String) {
    fun damageRoll() = damage.stream()
        .mapToInt { Dice().roll(it) }
        .sum()
}
