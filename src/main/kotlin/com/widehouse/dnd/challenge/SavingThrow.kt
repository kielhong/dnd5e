package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.AbilityType
import com.widehouse.dnd.character.CharacterOld
import com.widehouse.dnd.dice.Dice

class SavingThrow(private val characterOld: CharacterOld, private val abilityType: AbilityType, private val difficultClass: Int) {
    fun result(): Boolean {
        return Challenge.challenge(Dice.D20.roll(), modifiers(), difficultClass)
    }

    private fun modifiers(): List<Int> {
        val modifiers = mutableListOf<Int>()
        modifiers.add(characterOld.abilityByType(abilityType).modifier)
        if (characterOld.proficiencySavingThrow.contains(abilityType)) {
            modifiers.add(characterOld.proficiencyBonus)
        }

        return modifiers
    }
}
