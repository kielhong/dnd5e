package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.Skill
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class AbilityCheck(private val character: Character, private val skill: Skill, private val difficultClass: Int) {
    private val dice = Dice()

    fun result(): Boolean {
        return dice.roll(Die.D20) + character.abilityByType(skill.abilityType).modifier >= difficultClass
    }
}
// TODO : add proficiency bonus, if character has skill proficiency
