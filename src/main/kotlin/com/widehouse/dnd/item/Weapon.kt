package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class Weapon(name: String, private val damage: List<Die>, val itemType: String, val properties: List<WeaponProperty> = listOf()) : Item(name) {
    fun damageRoll() = damage.stream()
        .mapToInt { Dice().roll(it) }
        .sum()
}

enum class WeaponProperty {
    Finesse,
    Light,
    Heavy,
    Range,
    Thrown,
    TwoHanded,
    Versatile,
}
