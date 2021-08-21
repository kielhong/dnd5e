package com.widehouse.dnd.character

import com.widehouse.dnd.character.Size.Small

class MonsterFixtures {
    companion object {
        val goblin = Monster("goblin", Small, "Humanoid", abilities = Abilities(8, 14, 10, 10, 8, 8), hitPoints = 7, armorClass = 15)
    }
}
