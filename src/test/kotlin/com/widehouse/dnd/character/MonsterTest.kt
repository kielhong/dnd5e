package com.widehouse.dnd.character

import com.widehouse.dnd.character.Size.Small
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MonsterTest : FunSpec({
    test("monster construct") {
        val monster = Monster(name = "goblin", size = Small, type ="Humanoid", hitPoints = 7, abilities = Abilities(8, 14, 10, 10, 8, 8), armorClass = 15)

        monster.armorClass shouldBe 15
        monster.hitPoints shouldBe 7
        monster.strength.score shouldBe 8
        monster.size shouldBe Small
        monster.type shouldBe "Humanoid"
    }
})
