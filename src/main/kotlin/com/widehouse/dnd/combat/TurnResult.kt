package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character

class TurnResult(private val roundOrder: List<Character>) {
    fun resolve(): List<Character> {
        return roundOrder
            .filter { !it.dead() }
            .toList()
    }
}
