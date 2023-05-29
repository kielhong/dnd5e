package com.widehouse.dnd.character.player

import com.widehouse.dnd.challenge.RollResult
import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.PlayerCharacterFixtures
import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.character.ability.Ability
import com.widehouse.dnd.character.ability.Dexterity
import com.widehouse.dnd.character.ability.Strength
import com.widehouse.dnd.character.player.Class.Fighter
import com.widehouse.dnd.character.player.Race.Human
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.character.item.Weapon
import com.widehouse.dnd.character.item.WeaponProperty
import com.widehouse.dnd.character.item.WeaponType
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class PlayerCharacterAttackTest : FreeSpec({
    beforeEach {
        clearAllMocks()
    }

    "Attack Roll" - {
        val character = PlayerCharacterFixtures.rogue
        val dice = mockk<Dice>()
        val target = mockk<Character>()

        "without modifier" {
            io.kotest.data.forAll(
                table(
                    headers("roll", "armor class", "result"),
                    row(10, 10, RollResult.Success),
                    row(10, 11, RollResult.Fail),
                    row(11, 10, RollResult.Success),
                    row(1, 0, RollResult.CriticalFail),
                    row(20, 25, RollResult.CriticalSuccess)
                )
            ) { roll: Int, armorClass: Int, result: RollResult ->
                every { dice.roll() } returns roll
                every { target.armorClass } returns armorClass
                // then
                character.attackRoll(target, listOf(), dice) shouldBe result
            }
        }

        "with modifiers" {
            io.kotest.data.forAll(
                table(
                    headers("roll", "modifiers", "armor class", "result"),
                    row(8, listOf(1, 1), 10, RollResult.Success),
                    row(10, listOf(0), 8, RollResult.Success),
                    row(8, listOf(1), 10, RollResult.Fail)
                )
            ) { roll: Int, modifiers: List<Int>, armorClass: Int, result: RollResult ->
                every { dice.roll() } returns roll
                every { target.armorClass } returns armorClass
                // then
                character.attackRoll(target, modifiers, dice) shouldBe result
            }
        }
    }

    "Attack roll modifier" - {
        val character = PlayerCharacter.create("foo", Human, Fighter, Abilities.of(12, 8, 14, 15, 16, 17))
        val weapon = mockk<Weapon>()

        "melee weapon uses Strength modifier" {
            every { weapon.type } returns WeaponType.Melee
            every { weapon.properties } returns listOf()
            character.switchWeapon(weapon)

            val result = character.attackModifiers()
            result shouldBe Strength(12).modifier
        }

        "melee weapon which property is Finesse or Thrown uses Dexterity modifier" {
            every { weapon.type } returns WeaponType.Melee

            io.kotest.data.forAll(
                row(WeaponProperty.Finesse, Dexterity(8)),
                row(WeaponProperty.Thrown, Dexterity(8))
            ) { property: WeaponProperty, ability: Ability ->
                every { weapon.properties } returns listOf(property)
                character.switchWeapon(weapon)

                val result = character.attackModifiers()
                result shouldBe ability.modifier
            }
        }

        "range weapon uses Dexterity modifier" {
            every { weapon.type } returns WeaponType.Range
            character.switchWeapon(weapon)

            val result = character.attackModifiers()
            result shouldBe Dexterity(8).modifier
        }
    }

    "DamageRoll" - {
        val character = PlayerCharacterFixtures.fighter

        "weapon damage dice roll" {
            val weapon = mockk<Weapon>()
            every { weapon.damageRoll() } returns 8
            character.switchWeapon(weapon)
            // when
            val result = character.damageRoll()
            // then
            result shouldBe 8
            verify { weapon.damageRoll() }
        }
    }
})
