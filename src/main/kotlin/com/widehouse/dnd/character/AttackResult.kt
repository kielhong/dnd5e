package com.widehouse.dnd.character

class AttackResult(val target: Character, private val damage: Int) {
    fun resolve() {
        target.getDamage(damage)
    }
}
