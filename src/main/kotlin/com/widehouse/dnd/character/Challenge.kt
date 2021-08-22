package com.widehouse.dnd.character

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class Challenge(private val character: Character, private val skill: Skill, private val difficultClass: Int) {
    private val dice = Dice()

    fun result(): Boolean {
        return dice.roll(Die.D20) + character.abilityByType(skill.abilityType).modifier >= difficultClass
    }
}
