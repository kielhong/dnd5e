package com.widehouse.dnd

class AttackResult(private val damage: Int) {
    fun resolve(): Int {
        return damage
    }
}
