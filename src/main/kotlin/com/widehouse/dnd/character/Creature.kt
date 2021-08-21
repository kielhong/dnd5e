package com.widehouse.dnd.character

abstract class Creature() {
    abstract val armorClass: Int

    abstract fun attack(target: Creature): AttackResult

    abstract fun getDamage(point: Int)

    abstract fun dead(): Boolean
}