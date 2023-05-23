package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.CharacterOld
import com.widehouse.dnd.character.Skill
import com.widehouse.dnd.dice.Dice

class AbilityCheck(private val characterOld: CharacterOld, private val skill: Skill, private val difficultyClass: Int) {
    fun result(): Boolean {
        return Challenge.challenge(Dice.D20.roll(), modifiers(), difficultyClass)
    }

    private fun modifiers(): List<Int> {
        val modifiers = mutableListOf<Int>()
        modifiers.add(characterOld.abilityByType(skill.abilityType).modifier)
        if (characterOld.proficiencySkill.contains(skill)) {
            modifiers.add(characterOld.proficiencyBonus)
        }

        return modifiers
    }
}
