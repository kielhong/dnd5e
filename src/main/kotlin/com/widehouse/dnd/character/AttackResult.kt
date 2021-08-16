package com.widehouse.dnd.character

class AttackResult(private val damage: Int) {
    fun resolve(): Int {
        return damage
    }
}
