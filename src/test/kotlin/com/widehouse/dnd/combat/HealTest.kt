package com.widehouse.dnd.combat

import com.widehouse.dnd.character.CharacterFixtures.Companion.cleric
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HealTest : FunSpec({
    test("Heal character then get hitPoints") {
        val char = cleric(hp = 15)
        char.getDamage(10)
        char.removeDamage(5)
        char.hitPoints shouldBe char.maxHitPoints - 10 + 5
    }

    test("Heal cannot get hitPoints more than max hitPoints") {
        val char = cleric(hp = 15)
        char.removeDamage(5)
        char.hitPoints shouldBe char.maxHitPoints
    }
})
