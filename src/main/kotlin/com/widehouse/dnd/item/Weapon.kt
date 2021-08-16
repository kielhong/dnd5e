package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class Weapon(val name: String, private val damage: List<Die>, val itemType: String) {
    fun damageRoll() = damage.stream()
        .mapToInt { Dice().roll(it) }
        .sum()
}
