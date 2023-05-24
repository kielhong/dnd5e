package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.Monster
import com.widehouse.dnd.character.PlayerCharacter

class RoundResult(
    private val roundOrder: List<Character>,
    private val playerCharacters: List<PlayerCharacter>,
    private val monsters: List<Monster>
) {
//    fun endCombat(): Boolean {
//        var characterAllDead = true
//        var monsterAllDead = true
//
//        roundOrder
//            .forEach {
//                if (playerCharacters.contains(it)) characterAllDead = false
//                if (monsters.contains(it)) monsterAllDead = false
//            }
//        return characterAllDead || monsterAllDead
//    }
}
