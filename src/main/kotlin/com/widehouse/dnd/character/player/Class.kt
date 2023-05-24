package com.widehouse.dnd.character.player

import com.widehouse.dnd.character.ability.AbilityType
import com.widehouse.dnd.character.ability.AbilityType.Charisma
import com.widehouse.dnd.character.ability.AbilityType.Constitution
import com.widehouse.dnd.character.ability.AbilityType.Dexterity
import com.widehouse.dnd.character.ability.AbilityType.Intelligence
import com.widehouse.dnd.character.ability.AbilityType.Strength
import com.widehouse.dnd.character.ability.AbilityType.Wisdom
import com.widehouse.dnd.dice.Dice

enum class Class(val hitDice: Dice, val proficiencySavingThrow: List<AbilityType>) {
    Cleric(Dice.D8, listOf(Wisdom, Charisma)),
    Fighter(Dice.D10, listOf(Strength, Constitution)),
    Rogue(Dice.D8, listOf(Dexterity, Intelligence)),
    Wizard(Dice.D6, listOf(Intelligence, Wisdom))
}
