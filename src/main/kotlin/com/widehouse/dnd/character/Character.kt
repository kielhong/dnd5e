package com.widehouse.dnd.character

import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.dice.Dice

open class Character(
    open val name: String,
    open var abilities: Abilities,
    open var hitPoints: Int
) {
    fun attackRoll(target: Character, modifiers: List<Int>, dice: Dice): Boolean {
        return when (val roll = dice.roll()) {
            1 -> false
            20 -> true
            else -> roll + modifiers.sum() >= target.armorClass()
        }
    }

    open fun armorClass(): Int {
        return 0
    }
}
