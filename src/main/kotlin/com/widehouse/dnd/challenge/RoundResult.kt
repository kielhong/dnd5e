package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.PlayerCharacter
import com.widehouse.dnd.character.Creature
import com.widehouse.dnd.character.Monster

class RoundResult(
    private val roundOrder: List<Creature>,
    private val playerCharacters: List<PlayerCharacter>,
    private val monsters: List<Monster>
) {
    fun endCombat(): Boolean {
        var characterAllDead = true
        var monsterAllDead = true

        roundOrder
            .forEach {
                if (playerCharacters.contains(it)) characterAllDead = false
                if (monsters.contains(it)) monsterAllDead = false
            }
        return characterAllDead || monsterAllDead
    }
}
