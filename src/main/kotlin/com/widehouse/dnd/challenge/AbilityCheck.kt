package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.Skill
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class AbilityCheck(private val character: Character, private val skill: Skill, private val difficultClass: Int) {
    private val dice = Dice()

    fun result(): Boolean {
        return dice.roll(Die.D20) + bonus(character, skill) >= difficultClass
    }

    private fun bonus(character: Character, skill: Skill) =
        character.abilityByType(skill.abilityType).modifier +
            if (character.proficiencySkill.contains(skill)) character.proficiencyBonus else 0
}
