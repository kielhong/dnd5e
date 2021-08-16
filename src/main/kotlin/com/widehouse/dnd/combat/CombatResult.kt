package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character

class CombatResult(private val characters: List<Character>, private val monsters: List<Character>) {
    fun report(): List<Character> {
        return characters
    }
}
