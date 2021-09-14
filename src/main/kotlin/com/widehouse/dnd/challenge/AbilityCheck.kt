package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.Skill
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class AbilityCheck(private val character: Character, private val skill: Skill, private val difficultyClass: Int) {
    private val dice = Dice()

    fun result(): Boolean {
        return Challenge.challenge(dice.roll(Die.D20), modifiers(), difficultyClass)
    }

    private fun modifiers(): List<Int> {
        val modifiers = mutableListOf<Int>()
        modifiers.add(character.abilityByType(skill.abilityType).modifier)
        if (character.proficiencySkill.contains(skill)) {
            modifiers.add(character.proficiencyBonus)
        }

        return modifiers
    }
}
