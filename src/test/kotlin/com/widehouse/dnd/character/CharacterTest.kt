package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterClass.Fighter
import com.widehouse.dnd.character.Race.Dwarf
import com.widehouse.dnd.character.Race.Human
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CharacterTest : FunSpec({
    test("Character has name, class, level, race") {
        val char = Character(name = "foo", characterClass = Fighter, level = 1, race = Human, maxHitPoints = 20)
        char.name shouldBe "foo"
        char.characterClass shouldBe Fighter
        char.level shouldBe 1
        char.race shouldBe Human
    }

    test("Character ability init") {
        val char = Character("foo", Fighter, 1, Dwarf, Abilities(12, 13 , 14 ,15 ,16, 17), maxHitPoints = 20)

        char.strength.score() shouldBe 12
        char.dexterity.score() shouldBe 13
        char.constitution.score() shouldBe 14
        char.intelligence.score() shouldBe 15
        char.wisdom.score() shouldBe 16
        char.charisma.score() shouldBe 17
    }
})
