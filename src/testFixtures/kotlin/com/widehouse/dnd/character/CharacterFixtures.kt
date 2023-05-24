package com.widehouse.dnd.character

import com.widehouse.dnd.character.Class.Cleric
import com.widehouse.dnd.character.Class.Fighter
import com.widehouse.dnd.character.Class.Rogue
import com.widehouse.dnd.character.Class.Wizard
import com.widehouse.dnd.character.Race.Dwarf
import com.widehouse.dnd.character.Race.Elf
import com.widehouse.dnd.character.Race.Halfling
import com.widehouse.dnd.character.Race.Human

object CharacterFixtures {
    fun cleric() = PlayerCharacter("cleric", Cleric, 1, Dwarf, 0, Abilities.of(16, 18, 10, 10, 10, 10), emptyList(), emptyList(), 10)
    fun fighter() = PlayerCharacter("fighter", Fighter, 1, Human, 0, Abilities.of(18, 10, 10, 10, 10, 10), emptyList(), emptyList(), 12)
    fun rogue() = PlayerCharacter("rogue", Rogue, 1, Halfling, 0, Abilities.of(10, 18, 10, 10, 10, 10), emptyList(), emptyList(), 10)
    fun wizard() = PlayerCharacter("wizard", Wizard, 1, Elf, 0, Abilities.of(16, 18, 10, 10, 10, 10), emptyList(), emptyList(), 8)

    val foo: Character = Character("foo", Human, Fighter, Abilities.of(10, 10, 10, 10, 10, 10))
}
