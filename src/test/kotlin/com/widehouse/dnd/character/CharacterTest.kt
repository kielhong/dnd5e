package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterClass.Fighter
import com.widehouse.dnd.character.Race.Human
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CharacterTest : FunSpec({
    test("Character has name, class, level, race") {
        val char = Character(name = "foo", characterClass = Fighter, level = 1, race = Human, ability = mapOf(), maxHitPoints = 20)
        char.name shouldBe "foo"
        char.characterClass shouldBe Fighter
        char.level shouldBe 1
        char.race shouldBe Human
    }
})
