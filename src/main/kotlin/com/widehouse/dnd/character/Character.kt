package com.widehouse.dnd.character

import com.widehouse.dnd.dice.Dice

class Character(
    val name: String,
    val race: Race,
    val `class`: Class
) {
    fun attackRoll(target: Character, modifiers: List<Int>, dice: Dice): Boolean {
        return when (val roll = dice.roll()) {
            1 -> false
            20 -> true
            else -> roll + modifiers.sum() >= target.armorClass()
        }
    }

    fun armorClass(): Int {
        TODO("Not yet implemented")
    }
}
