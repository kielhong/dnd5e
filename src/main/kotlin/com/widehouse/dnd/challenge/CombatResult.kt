package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.CharacterOld
import com.widehouse.dnd.character.Monster

class CombatResult(private val characterOlds: List<CharacterOld>, private val monsters: List<Monster>) {
    fun report(): List<CharacterOld> {
        return characterOlds
    }
}
