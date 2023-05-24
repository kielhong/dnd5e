package com.widehouse.dnd.character

import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.character.action.AttackResult

abstract class Creature(
    abilities: Abilities
) {
    val strength = abilities.strength
    val dexterity = abilities.dexterity
    val constitution = abilities.constitution
    val intelligence = abilities.intelligence
    val wisdom = abilities.wisdom
    val charisma = abilities.charisma

    open var hitPoints: Int = 0
    open val armorClass: Int = 0

    abstract fun attack(target: Creature): AttackResult

    abstract fun getDamage(point: Int)

    abstract fun dead(): Boolean
}
