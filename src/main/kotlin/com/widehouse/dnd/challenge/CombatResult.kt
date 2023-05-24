package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.PlayerCharacter
import com.widehouse.dnd.character.Monster

class CombatResult(private val playerCharacters: List<PlayerCharacter>, private val monsters: List<Monster>) {
    fun report(): List<PlayerCharacter> {
        return playerCharacters
    }
}
