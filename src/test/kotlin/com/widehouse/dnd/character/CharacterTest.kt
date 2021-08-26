package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterFixtures.Companion.fighter
import com.widehouse.dnd.character.Class.Fighter
import com.widehouse.dnd.character.Race.Dwarf
import com.widehouse.dnd.character.Race.Human
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.spyk

class CharacterTest : FunSpec({
    test("Character has name, class, level, race, xp") {
        val char = Character(name = "foo", `class` = Fighter, level = 1, race = Human, experiencePoints = 10, maxHitPoints = 20)
        char.name shouldBe "foo"
        char.`class` shouldBe Fighter
        char.level shouldBe 1
        char.race shouldBe Human
        char.experiencePoints shouldBe 10
        char.hitPoints shouldBe 20
    }

    test("Character ability init") {
        val char = Character("foo", Fighter, 1, Dwarf, 0, Abilities(12, 13, 14, 15, 16, 17), maxHitPoints = 20)

        char.strength.score shouldBe 12
        char.dexterity.score shouldBe 13
        char.constitution.score shouldBe 14
        char.intelligence.score shouldBe 15
        char.wisdom.score shouldBe 16
        char.charisma.score shouldBe 17
    }

    test("Character get xp over 300 then advance level 2") {
        val char = spyk(fighter())
        char.earnExperiencePoints(300)

        char.experiencePoints shouldBe 300
        char.level shouldBe 2
    }

    test("Level enum test") {
        for (lv in 1..5) {
            LevelTable.of(lv) shouldBe LevelTable.valueOf("Level$lv")
        }
    }
})
