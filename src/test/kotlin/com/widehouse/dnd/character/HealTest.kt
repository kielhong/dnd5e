package com.widehouse.dnd.character

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HealTest : FunSpec({
    test("Heal character then get hitPoints") {
        val char = Character(ability = mapOf(), level = 2, maxHitPoints = 15)
        char.getDamage(10)
        char.removeDamage(5)
        char.hitPoints() shouldBe 10
    }

    test("Heal cannot get hitPoints more than max hitPoints") {
        val char = Character(ability = mapOf(), level = 2, maxHitPoints = 15)
        char.removeDamage(5)
        char.hitPoints() shouldBe 15
    }
})
