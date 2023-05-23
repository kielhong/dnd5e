package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.CharacterOld
import com.widehouse.dnd.character.Creature
import com.widehouse.dnd.character.Monster

class RoundResult(
    private val roundOrder: List<Creature>,
    private val characterOlds: List<CharacterOld>,
    private val monsters: List<Monster>
) {
    fun endCombat(): Boolean {
        var characterAllDead = true
        var monsterAllDead = true

        roundOrder
            .forEach {
                if (characterOlds.contains(it)) characterAllDead = false
                if (monsters.contains(it)) monsterAllDead = false
            }
        return characterAllDead || monsterAllDead
    }
}
