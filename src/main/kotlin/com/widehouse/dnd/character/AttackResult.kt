package com.widehouse.dnd.character

class AttackResult(val target: Creature, private val damage: Int) {
    fun resolve() {
        target.getDamage(damage)
    }
}
