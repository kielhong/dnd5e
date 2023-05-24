package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.ability.AbilityType
import com.widehouse.dnd.character.player.PlayerCharacter
import com.widehouse.dnd.dice.Dice

class SavingThrow(private val playerCharacter: PlayerCharacter, private val abilityType: AbilityType, private val difficultClass: Int) {
    fun result(): Boolean {
        return Challenge.challenge(Dice.D20.roll(), modifiers(), difficultClass)
    }

    private fun modifiers(): List<Int> {
        val modifiers = mutableListOf<Int>()
        modifiers.add(playerCharacter.abilityByType(abilityType).modifier)
        if (playerCharacter.proficiencySavingThrow.contains(abilityType)) {
            modifiers.add(playerCharacter.proficiencyBonus)
        }

        return modifiers
    }
}
