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
        fun cleric(hp: Int = 20) =
            Character("cleric", Cleric, 2, Dwarf, Abilities(16, 18, 10, 10, 10, 10), maxHitPoints = hp)
        fun fighter(hp: Int = 20) =
            Character("fighter", Fighter, 2, Human, Abilities(18, 10, 10, 10, 10, 10), maxHitPoints = hp)
        fun rogue(hp: Int = 20) =
            Character("rogue", Rogue, 2, Halfling, Abilities(10, 18, 10, 10, 10, 10), maxHitPoints = hp)
        fun wizard(hp: Int = 20) =
            Character("wizard", Wizard, 2, Elf, Abilities(16, 18, 10, 10, 10, 10), maxHitPoints = hp)
    }
}
