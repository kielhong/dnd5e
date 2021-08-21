package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterClass.Cleric
import com.widehouse.dnd.character.CharacterClass.Fighter
import com.widehouse.dnd.character.CharacterClass.Rogue
import com.widehouse.dnd.character.CharacterClass.Wizard
import com.widehouse.dnd.character.Race.Dwarf
import com.widehouse.dnd.character.Race.Elf
import com.widehouse.dnd.character.Race.Halfling
import com.widehouse.dnd.character.Race.Human
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.item.Weapon

class CharacterFixtures {
    companion object {
        fun cleric(hp: Int = 20, dice: Dice = Dice()) = Character("cleric", Cleric, 2, Dwarf, ability = mapOf("str" to Strength(16), "dex" to Dexterity(18)), maxHitPoints = hp, dice = dice)
        fun fighter(hp: Int = 20, weapon: Weapon = Weapon("", listOf(), ""), dice: Dice = Dice()) =
            Character("fighter", Fighter, 2, Human, ability = mapOf("str" to Strength(18)), maxHitPoints = hp, weapon = weapon, dice = dice)
        fun rogue(hp: Int = 20, weapon: Weapon = Weapon("", listOf(), ""), dice: Dice = Dice()) =
            Character("rogue", Rogue, 2, Halfling, ability = mapOf("str" to Strength(12), "dex" to Dexterity(18)), weapon = weapon, maxHitPoints = hp, dice = dice)
        fun wizard(hp: Int = 20, dice: Dice = Dice()) = Character("wizard", Wizard, 2, Elf, ability = mapOf("str" to Strength(16), "dex" to Dexterity(18)), maxHitPoints = hp, dice = dice)
    }
}
