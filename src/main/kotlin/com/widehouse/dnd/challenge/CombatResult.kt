package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.nonplayer.Monster
import com.widehouse.dnd.character.player.PlayerCharacter

class CombatResult(private val playerCharacters: List<PlayerCharacter>, private val monsters: List<Monster>) {
    fun report(): List<PlayerCharacter> {
        return playerCharacters
    }
}
