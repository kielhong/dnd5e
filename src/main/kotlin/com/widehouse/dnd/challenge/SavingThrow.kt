package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.AbilityType
import com.widehouse.dnd.character.Character
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class SavingThrow(private val character: Character, private val abilityType: AbilityType, private val difficultClass: Int) {
    private val dice = Dice()

    fun result(): Boolean {
        return Challenge.challenge(dice.roll(Die.D20), modifiers(), difficultClass)
    }

    private fun modifiers(): List<Int> {
        val modifiers = mutableListOf<Int>()
        modifiers.add(character.abilityByType(abilityType).modifier)
        if (character.proficiencySavingThrow.contains(abilityType)) {
            modifiers.add(character.proficiencyBonus)
        }

        return modifiers
    }
}
