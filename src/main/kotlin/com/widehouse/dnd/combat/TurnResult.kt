package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.Creature

class TurnResult(private val roundOrder: List<Creature>) {
    fun resolve(): List<Creature> {
        return roundOrder
            .filter { !it.dead() }
            .toList()
    }
}
