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

    fun combat() {
        do {
            round()
            println("round")
        } while (!playerCharacters.all { it.dead() } && !monsters.all { it.dead() })
    }

    fun round() {
        initiativeOrder.forEach {
            takeTurn(it)
        }
    }

    fun takeTurn(character: Character) {
        if (character is PlayerCharacter) {
            action(character, monsters[0])
        }
        if (character is Monster) {
            action(character, playerCharacters[0])
        }
    }

    fun action(character: Character, target: Character) {
    }
}
