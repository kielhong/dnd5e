package com.widehouse.dnd.character.action

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.CharacterFixtures.Companion.fighter
import com.widehouse.dnd.character.Monster
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die.D20
import com.widehouse.dnd.dice.RollCondition.DISADVANTAGE
import com.widehouse.dnd.item.Weapon
import com.widehouse.dnd.item.WeaponProperty.Finesse
import com.widehouse.dnd.item.WeaponProperty.Thrown
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify

class AttackRollTest : FunSpec({
    val dice = mockk<Dice>()
    val target = mockk<Monster>()
    val char = spyk(fighter(), recordPrivateCalls = true)

    beforeEach {
        val f = Character::class.java.getDeclaredField("dice")
        f.isAccessible = true
        f.set(char, dice)
    }

    afterEach {
        clearAllMocks()
    }

    test("Attack Roll then hit") {
        every { dice.roll(D20) }.returns(15)
        every { target.armorClass }.returns(16)

        char.attackRoll(target)

        verify { char["attackModifier"](allAny<Weapon>()) }
        verify { char.proficiencyBonus }
        verify { target.armorClass }
    }

    test("Roll Dice 1 then AttackRoll should fail") {
        every { dice.roll(D20) }.returns(1)

        char.attackRoll(target) shouldBe false
        verify(exactly = 0) { char["attackModifier"](allAny<Weapon>()) }
        verify(exactly = 0) { char.proficiencyBonus }
    }

    test("Roll Dice 20 then AttackRoll should succeed") {
        every { dice.roll(D20) }.returns(20)

        char.attackRoll(target) shouldBe true
        verify(exactly = 0) { char["attackModifier"](allAny<Weapon>()) }
        verify(exactly = 0) { char.proficiencyBonus }
    }

    test("Attack with Finesse or Thrown weapon uses dex modifier") {
        every { dice.roll(D20) } returns 10
        every { target.armorClass } returns 13
        val weapon = mockk<Weapon>()
        char.equip(weapon)

        listOf(Finesse, Thrown).forAll {
            every { weapon.properties }.returns(listOf(it))
            char.attackRoll(target)

            verify { char.dexterity }
            verify(exactly = 0) { char.strength }
        }
    }

    test("Ranged Attack in Long range") {
        every { dice.roll(D20, DISADVANTAGE) }.returns(5)
        every { target.armorClass }.returns(13)
        val weapon = mockk<Weapon>()
        every { weapon.properties }.returns(emptyList())
        char.equip(weapon)

        char.attackRoll(target, DISADVANTAGE) shouldBe false
    }
})
