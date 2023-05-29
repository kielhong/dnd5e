package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.nonplayer.Monster
import com.widehouse.dnd.character.player.PlayerCharacter
import com.widehouse.dnd.dice.Dice

class Combat(
    val playerCharacters: List<PlayerCharacter>,
    val monsters: List<Monster>
) {
    lateinit var initiativeOrder: List<Character>

    fun initiativeRoll(dice: Dice) {
        initiativeOrder = (playerCharacters + monsters)
            .associateWith { dice.roll() + it.abilities.dexterity.modifier }
            .toList()
            .sortedBy { (_, value) -> value }.reversed()
            .map { (character, _) -> character }
    }

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
