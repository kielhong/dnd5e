package com.widehouse.dnd.character

abstract class Creature(
    abilities: Abilities
) {
    val strength = Strength(abilities.str)
    val dexterity = Dexterity(abilities.dex)
    val constitution = Constitution(abilities.con)
    val intelligence = Intelligence(abilities.int)
    val wisdom = Wisdom(abilities.wis)
    val charisma = Charisma(abilities.cha)

    open var hitPoints: Int = 0
    open var armorClass: Int = 0

    abstract fun attack(target: Creature): AttackResult

    abstract fun getDamage(point: Int)

    abstract fun dead(): Boolean
}
