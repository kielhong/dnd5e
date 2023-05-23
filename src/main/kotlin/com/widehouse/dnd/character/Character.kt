package com.widehouse.dnd.character

import com.widehouse.dnd.dice.Dice

class Character(
    val name: String,
    val race: Race,
    val `class`: Class
) {
    fun hit(character: Character, dice: Dice): Boolean {
        return when (val rollResult = dice.roll()) {
            1 -> false
            20 -> true
            else -> rollResult >= character.armorClass()
        }
    }

    fun armorClass(): Int {
        TODO("Not yet implemented")
    }
}
