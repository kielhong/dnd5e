package com.widehouse.dnd.character

import com.widehouse.dnd.character.Class.Fighter
import com.widehouse.dnd.character.Race.Human

object CharacterFixtures {
    val foo: Character = Character("foo", Human, Fighter, Abilities.of(10, 10, 10, 10, 10, 10), 10)
}
