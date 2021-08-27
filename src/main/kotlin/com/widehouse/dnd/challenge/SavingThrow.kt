package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.AbilityType
import com.widehouse.dnd.character.Character
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class SavingThrow(private val character: Character, private val abilityType: AbilityType, private val difficultClass: Int) {
    private val dice = Dice()

    fun result(): Boolean {
        return dice.roll(Die.D20) + bonus(character, abilityType) >= difficultClass
    }

    private fun bonus(character: Character, abilityType: AbilityType) =
        character.abilityByType(abilityType).modifier +
            if (character.proficiencySavingThrow.contains(abilityType)) character.proficiencyBonus else 0
}
