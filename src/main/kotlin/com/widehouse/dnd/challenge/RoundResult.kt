package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.Creature
import com.widehouse.dnd.character.Monster

class RoundResult(
    private val roundOrder: List<Creature>,
    private val characters: List<Character>,
    private val monsters: List<Monster>
) {
    fun endCombat(): Boolean {
        var characterAllDead = true
        var monsterAllDead = true

        roundOrder
            .forEach {
                if (characters.contains(it)) characterAllDead = false
                if (monsters.contains(it)) monsterAllDead = false
            }
        return characterAllDead || monsterAllDead
    }
}
