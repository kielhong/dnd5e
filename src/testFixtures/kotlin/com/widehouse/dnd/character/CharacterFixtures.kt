package com.widehouse.dnd.character

import com.widehouse.dnd.character.Class.Cleric
import com.widehouse.dnd.character.Class.Fighter
import com.widehouse.dnd.character.Class.Rogue
import com.widehouse.dnd.character.Class.Wizard
import com.widehouse.dnd.character.Race.Dwarf
import com.widehouse.dnd.character.Race.Elf
import com.widehouse.dnd.character.Race.Halfling
import com.widehouse.dnd.character.Race.Human

class CharacterFixtures {
    companion object {
        fun cleric() =
            Character("cleric", Cleric, 1, Dwarf, 0, Abilities(16, 18, 10, 10, 10, 10), emptyList(), emptyList(), 10)
        fun fighter() =
            Character("fighter", Fighter, 1, Human, 0, Abilities(18, 10, 10, 10, 10, 10), emptyList(), emptyList(), 12)
        fun rogue() =
            Character("rogue", Rogue, 1, Halfling, 0, Abilities(10, 18, 10, 10, 10, 10), emptyList(), emptyList(), 10)
        fun wizard() =
            Character("wizard", Wizard, 1, Elf, 0, Abilities(16, 18, 10, 10, 10, 10), emptyList(), emptyList(), 8)
    }
}
