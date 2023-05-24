package com.widehouse.dnd.character.player

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.PlayerCharacterFixtures
import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.character.ability.AbilityType
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.item.ItemFixtures
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify

class PlayerCharacterTest : FreeSpec({
    beforeEach {
        clearAllMocks()
    }

    "Player Character creation test" {
        val character = PlayerCharacter.create("foo", Race.Human, Class.Fighter, Abilities.of(15, 14, 13, 12, 10, 8))

        assertSoftly(character) {
            name shouldBe "foo"
            race shouldBe Race.Human
            `class` shouldBe Class.Fighter
            level shouldBe 1
            abilities.strength.score shouldBe 15
            abilities.constitution.score shouldBe 13
            hitPoints shouldBe 11
            proficiencyBonus shouldBe 2
            proficiencySavingThrow shouldContain AbilityType.Strength
            proficiencySavingThrow shouldContain AbilityType.Constitution
        }
    }

    "Player Character ability init" {
        val character = PlayerCharacter.create("foo", Race.Human, Class.Fighter, Abilities.of(12, 13, 14, 15, 16, 17))

        assertSoftly(character) {
            abilities.strength.score shouldBe 12
            abilities.dexterity.score shouldBe 13
            abilities.constitution.score shouldBe 14
            abilities.intelligence.score shouldBe 15
            abilities.wisdom.score shouldBe 16
            abilities.charisma.score shouldBe 17
        }
    }

    "Attack" - {
        val character = spyk(PlayerCharacterFixtures.fighter)
        val target = mockk<Character>()
        val dice = mockk<Dice>()

        "on hit - return damage roll value" {
            character.equipment.mainHand = ItemFixtures.longSword
            every { target.armorClass } returns 1
            every { dice.roll() } returns 19
            val result = character.attack(target, dice)
            // then
            result shouldBeGreaterThanOrEqual 0
            verify { character.dealDamage() }
        }

        "on miss - return 0 and not roll damage" {
            every { target.armorClass } returns 20
            every { dice.roll() } returns 2
            val result = character.attack(target, dice)
            // then
            result shouldBe 0
            verify(exactly = 0) { character.dealDamage() }
        }
    }

    "hit other target" - {
        val character = PlayerCharacterFixtures.rogue
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
                every { target.armorClass } returns armorClass
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
                every { target.armorClass } returns armorClass
                // then
                character.attackRoll(target, modifiers, dice) shouldBe result
            }
        }
    }
})
