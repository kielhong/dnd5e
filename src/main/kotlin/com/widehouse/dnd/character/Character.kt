package com.widehouse.dnd.character

import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.RollResult

abstract class Character(
    open val name: String,
    open var abilities: Abilities,
    open var hitPoints: Int
) {
    abstract val armorClass: Int

    abstract fun attackRoll(target: Character, modifiers: List<Int>, dice: Dice): RollResult

    abstract fun damageRoll(): Int

    abstract fun getDamage(damage: Int)

    abstract fun dead(): Boolean
}
