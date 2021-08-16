package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character

class RoundResult(
    private val roundOrder: List<Character>,
    private val characters: List<Character>,
    private val monsters: List<Character>
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
