package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.nonplayer.Monster
import com.widehouse.dnd.character.player.PlayerCharacter

class Combat(val playerCharacters: List<PlayerCharacter>, val monsters: List<Monster>) {
    private lateinit var roundOrder: List<Character>

//    fun initiative() {
//        roundOrder = playerCharacters + monsters
//            .associateWith { Dice.D20.roll() + it.dexterity.modifier }
//            .toList()
//            .sortedBy { (_, value) -> value }.reversed()
//            .map { (key, _) -> key }
//    }
//
//    fun roundOrder(): List<Creature> {
//        return roundOrder
//    }
//
//    fun combat(): CombatResult {
//        do {
//            val roundResult = round()
//        } while (!roundResult.endCombat())
//
//        return CombatResult(playerCharacters, monsters)
//    }
//
//    fun round(): RoundResult {
//        roundOrder.map {
//            val turnResult = turn(it)
//            roundOrder = turnResult.resolve()
//        }
//        return RoundResult(roundOrder, playerCharacters, monsters)
//    }
//
//    fun turn(character: Creature): TurnResult {
//        if (playerCharacters.contains(character)) {
//            character.attack(monsters[0])
//        }
//        if (monsters.contains(character)) {
//            character.attack(playerCharacters[0])
//        }
//
//        return TurnResult(roundOrder)
//    }
}
