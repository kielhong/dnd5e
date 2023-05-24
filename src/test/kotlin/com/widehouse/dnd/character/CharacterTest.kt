package com.widehouse.dnd.character

import com.widehouse.dnd.dice.Dice
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CharacterTest : FreeSpec() {
    init {
        "Character has name, class, race" {
            val character = Character("foo", Abilities.of(10, 10, 10, 10, 10, 10), 20)
            // then
            character.name shouldBe "foo"
            character.abilities.strength.score shouldBe 10
            character.hitPoints shouldBe 20
        }

        "Character ability init" {
            val char = Character("foo", Abilities.of(12, 13, 14, 15, 16, 17), 20)

            char.abilities.strength.score shouldBe 12
            char.abilities.dexterity.score shouldBe 13
            char.abilities.constitution.score shouldBe 14
            char.abilities.intelligence.score shouldBe 15
            char.abilities.wisdom.score shouldBe 16
            char.abilities.charisma.score shouldBe 17
        }

        "hit other target" - {
            val character = CharacterFixtures.foo
            val dice = mockk<Dice>()
            val target = mockk<Character>()

            "without modifier" {
                io.kotest.data.forAll(
                    table(
                        headers("roll", "armor class", "result"),
                        row(10, 10, true),
                        row(10, 11, false),
                        row(11, 10, true),
                        row(1, 0, false), // 무조건 실패
                        row(20, 25, true) // 무조건 성공
                    )
                ) { roll: Int, armorClass: Int, result: Boolean ->
                    every { dice.roll() } returns roll
                    every { target.armorClass() } returns armorClass
                    // then
                    character.attackRoll(target, listOf(0), dice) shouldBe result
                }
            }

            "with modifiers" {
                io.kotest.data.forAll(
                    table(
                        headers("roll", "modifiers", "armor class", "result"),
                        row(8, listOf(1, 1), 10, true),
                        row(10, listOf(0), 8, true),
                        row(8, listOf(1), 10, false)
                    )
                ) { roll: Int, modifiers: List<Int>, armorClass: Int, result: Boolean ->
                    every { dice.roll() } returns roll
                    every { target.armorClass() } returns armorClass
                    // then
                    character.attackRoll(target, modifiers, dice) shouldBe result
                }
            }
        }
    }

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
}
