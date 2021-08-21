package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.Monster

class CombatResult(private val characters: List<Character>, private val monsters: List<Monster>) {
    fun report(): List<Character> {
        return characters
    }
}
