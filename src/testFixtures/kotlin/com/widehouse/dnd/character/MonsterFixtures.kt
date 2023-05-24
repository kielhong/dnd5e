package com.widehouse.dnd.character

import com.widehouse.dnd.character.nonplayer.Monster
import com.widehouse.dnd.character.nonplayer.MonsterSize.Small

class MonsterFixtures {
    companion object {
        val goblin = Monster("goblin", Abilities.of(8, 14, 10, 10, 8, 8), hitPoints = 7, Small, "Humanoid", 15)
    }
}
