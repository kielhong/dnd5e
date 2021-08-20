package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterClass.Cleric
import com.widehouse.dnd.character.CharacterClass.Fighter
import com.widehouse.dnd.character.CharacterClass.Rogue
import com.widehouse.dnd.character.CharacterClass.Wizard
import com.widehouse.dnd.character.Race.Dwarf
import com.widehouse.dnd.character.Race.Elf
import com.widehouse.dnd.character.Race.Halfling
import com.widehouse.dnd.character.Race.Human

class CharacterFixtures {
    companion object {
        private val cleric = Character("cleric", Cleric, 2, Dwarf, ability = mapOf("str" to Strength(16), "dex" to Dexterity(18)), maxHitPoints = 20)
        private val fighter = Character("fighter", Fighter, 2, Human, ability = mapOf("str" to Strength(18)), maxHitPoints = 20)
        private val rogue = Character("rogue", Rogue, 2, Halfling, ability = mapOf("dex" to Dexterity(18)), maxHitPoints = 20)
        private val wizard = Character("wizard", Wizard, 2, Elf, ability = mapOf("dex" to Dexterity(18)), maxHitPoints = 20)

        fun cleric() = cleric
        fun fighter() = fighter
        fun rogue() = rogue
        fun wizard() = wizard
    }
}
