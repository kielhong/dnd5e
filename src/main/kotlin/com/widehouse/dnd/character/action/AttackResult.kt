package com.widehouse.dnd.character.action

import com.widehouse.dnd.character.Character

class AttackResult(val target: Character, private val damage: Int) {
    fun resolve() {
        target.getDamage(damage)
    }
}
