package com.widehouse.dnd.character

import com.widehouse.dnd.character.AbilityType.Charisma
import com.widehouse.dnd.character.AbilityType.Constitution
import com.widehouse.dnd.character.AbilityType.Dexterity
import com.widehouse.dnd.character.AbilityType.Intelligence
import com.widehouse.dnd.character.AbilityType.Strength
import com.widehouse.dnd.character.AbilityType.Wisdom
import com.widehouse.dnd.dice.Die

enum class Class(val hitDice: Die, val proficiencySavingThrow: List<AbilityType>) {
    Cleric(Die.D8, listOf(Wisdom, Charisma)),
    Fighter(Die.D10, listOf(Strength, Constitution)),
    Rogue(Die.D8, listOf(Dexterity, Intelligence)),
    Wizard(Die.D6, listOf(Intelligence, Wisdom))
}
