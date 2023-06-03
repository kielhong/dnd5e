package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.nonplayer.Monster
import com.widehouse.dnd.character.player.PlayerCharacter
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.RollResult

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

    private fun takeTurn(character: Character) {
        actionAttack(character)
    }

    fun actionAttack(character: Character) {
        val target = chooseTarget(character)
        val modifiers = if (character is PlayerCharacter) {
            listOf(character.attacks.modifier(), character.proficiencyBonus)
        } else {
            emptyList()
        }
        val attackRollResult = attackRoll(target, modifiers, Dice.D20)
        if (attackRollResult == RollResult.CriticalSuccess || attackRollResult == RollResult.Success) {
            val damage = character.damageRoll()
            target.hitPoints = target.hitPoints - damage
        }
    }

    private fun chooseTarget(character: Character): Character {
        return if (character is PlayerCharacter) {
            monsters.first { !it.dead() }
        } else {
            playerCharacters.first { !it.dead() }
        }
    }

    fun attackRoll(target: Character, modifiers: List<Int>, dice: Dice): RollResult {
        return when (val roll = dice.roll()) {
            1 -> RollResult.CriticalFail
            20 -> RollResult.CriticalSuccess
            else -> if (roll + modifiers.sum() >= target.armorClass) RollResult.Success else RollResult.Fail
        }
    }
}
