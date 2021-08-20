package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.CharacterClass.Cleric
import com.widehouse.dnd.character.Race.Dwarf
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HealTest : FunSpec({
    test("Heal character then get hitPoints") {
        val char = Character("foo", Cleric, 2, Dwarf, ability = mapOf(), maxHitPoints = 15)
        char.getDamage(10)
        char.removeDamage(5)
        char.hitPoints() shouldBe 10
    }

    test("Heal cannot get hitPoints more than max hitPoints") {
        val char = Character("foo", Cleric, 2, Dwarf, ability = mapOf(), maxHitPoints = 15)
        char.removeDamage(5)
        char.hitPoints() shouldBe 15
    }
})
