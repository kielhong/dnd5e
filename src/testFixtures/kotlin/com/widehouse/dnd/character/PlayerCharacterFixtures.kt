package com.widehouse.dnd.character

import com.widehouse.dnd.character.player.Class
import com.widehouse.dnd.character.player.PlayerCharacter
import com.widehouse.dnd.character.player.Race

object PlayerCharacterFixtures {
    val cleric: PlayerCharacter = PlayerCharacter(
        "cleric",
        Race.Dwarf,
        Class.Cleric,
        Abilities.of(16, 18, 10, 10, 10, 10),
        1,
        0,
        emptyList(),
        emptyList(),
        10
    )
    val fighter: PlayerCharacter = PlayerCharacter(
        "fighter",
        Race.Human,
        Class.Fighter,
        Abilities.of(18, 10, 10, 10, 10, 10),
        1,
        0,
        emptyList(),
        emptyList(),
        12
    )
    val rogue: PlayerCharacter = PlayerCharacter(
        "rogue",
        Race.Halfling,
        Class.Rogue,
        Abilities.of(10, 18, 10, 10, 10, 10),
        1,
        0,
        emptyList(),
        emptyList(),
        10
    )
    val wizard: PlayerCharacter = PlayerCharacter(
        "wizard",
        Race.Elf,
        Class.Wizard,
        Abilities.of(16, 18, 10, 10, 10, 10),
        1,
        0,
        emptyList(),
        emptyList(),
        8
    )
}
