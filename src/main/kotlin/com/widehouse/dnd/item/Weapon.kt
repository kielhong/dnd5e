package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class Weapon(name: String, private val damage: List<Die>, val itemType: String) : Item(name) {
    fun damageRoll() = damage.stream()
        .mapToInt { Dice().roll(it) }
        .sum()
}
