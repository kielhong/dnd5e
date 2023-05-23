package com.widehouse.dnd.character

import com.widehouse.dnd.character.Class.Fighter
import com.widehouse.dnd.character.Race.Human
import com.widehouse.dnd.dice.Dice
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CharacterTest : FunSpec({
    test("Character has name, class, race") {
        val character = Character(name = "foo", race = Human, `class` = Fighter)
        character.name shouldBe "foo"
        character.`class` shouldBe Fighter
        character.race shouldBe Human
    }

    test("hit other character") {
        val foo = CharacterFixtures.foo
        val dice = mockk<Dice>()
        val bar = mockk<Character>()
        io.kotest.data.forAll(
            table(
                headers("roll", "armor class", "result"),
                row(10, 10, true),
                row(10, 11, false),
                row(11, 10, true),
                row(1, 0, false),   // 무조건 실패
                row(20, 25, true)   // 무조건 성공
            )
        ) { roll: Int, armorClass: Int, result: Boolean ->
            every { dice.roll() } returns roll
            every { bar.armorClass() } returns armorClass
            // then
            foo.hit(bar, dice) shouldBe result
        }
    }

//    test("Character has name, class, level, race, xp") {
//        val char = CharacterOld(name = "foo", `class` = Fighter, level = 1, race = Human, experiencePoints = 10, maxHitPoints = 20)
//        char.name shouldBe "foo"
//        char.`class` shouldBe Fighter
//        char.level shouldBe 1
//        char.race shouldBe Human
//        char.experiencePoints shouldBe 10
//        char.hitPoints shouldBe 20
//    }
//
//    test("Character ability init") {
//        val char = CharacterOld("foo", Fighter, 1, Dwarf, 0, Abilities(12, 13, 14, 15, 16, 17), maxHitPoints = 20)
//
//        char.strength.score shouldBe 12
//        char.dexterity.score shouldBe 13
//        char.constitution.score shouldBe 14
//        char.intelligence.score shouldBe 15
//        char.wisdom.score shouldBe 16
//        char.charisma.score shouldBe 17
//    }
//
//    test("Character get xp over 300 then advance level 2") {
//        val char = spyk(fighter())
//        char.earnExperiencePoints(300)
//
//        char.experiencePoints shouldBe 300
//        char.level shouldBe 2
//    }
//
//    test("Level enum test") {
//        for (lv in 1..5) {
//            LevelTable.of(lv) shouldBe LevelTable.valueOf("Level$lv")
//        }
//    }
//
//    test("character creation test") {
//        val char = CharacterOld.create(name = "foo", race = Human, `class` = Fighter, abilities = Abilities(15, 14, 13, 12, 10, 8))
//
//        assertSoftly(char) {
//            it.name shouldBe "foo"
//            it.race shouldBe Human
//            it.`class` shouldBe Fighter
//            it.level shouldBe 1
//            it.strength shouldBe Strength(15)
//            it.constitution shouldBe Constitution(13)
//            it.hitPoints shouldBe 11
//            it.proficiencyBonus shouldBe 2
//            it.proficiencySavingThrow shouldContain AbilityType.Strength
//            it.proficiencySavingThrow shouldContain AbilityType.Constitution
//        }
//    }
})
