package com.widehouse.dnd.combat

import com.widehouse.dnd.character.AbilityType
import com.widehouse.dnd.character.Character
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class SavingThrow(private val character: Character, private val abilityType: AbilityType, private val difficultClass: Int) {
    private val dice = Dice()

    fun result(): Boolean {
        return dice.roll(Die.D20) + character.abilityByType(abilityType).modifier >= difficultClass
    }
}
// TODO : add proficiency bonus, if character has saving throw proficiency
