package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.Skill
import com.widehouse.dnd.character.player.PlayerCharacter
import com.widehouse.dnd.dice.Dice

class AbilityCheck(private val playerCharacter: PlayerCharacter, private val skill: Skill, private val difficultyClass: Int) {
    fun result(): Boolean {
        return Challenge.challenge(Dice.D20.roll(), modifiers(), difficultyClass)
    }

    private fun modifiers(): List<Int> {
        val modifiers = mutableListOf<Int>()
        modifiers.add(playerCharacter.abilityByType(skill.abilityType).modifier)
        if (playerCharacter.proficiencySkill.contains(skill)) {
            modifiers.add(playerCharacter.proficiencyBonus)
        }

        return modifiers
    }
}
