package com.widehouse.dnd.character.action

import com.widehouse.dnd.character.Creature

class AttackResult(val target: Creature, private val damage: Int) {
    fun resolve() {
        target.getDamage(damage)
    }
}
