package com.widehouse.dnd.character.action

import com.widehouse.dnd.character.nonplayer.Monster
import com.widehouse.dnd.character.player.PlayerCharacter
import com.widehouse.dnd.character.PlayerCharacterFixtures.fighter
import com.widehouse.dnd.dice.Dice
import io.kotest.core.spec.style.FunSpec
import io.mockk.clearAllMocks
import io.mockk.mockk
import io.mockk.spyk

class AttackRollTest : FunSpec({
    val dice = mockk<Dice>()
    val target = mockk<Monster>()
    val char = spyk(fighter, recordPrivateCalls = true)

    beforeEach {
        val f = PlayerCharacter::class.java.getDeclaredField("dice")
        f.isAccessible = true
        f.set(char, dice)
    }

    afterEach {
        clearAllMocks()
    }

//    test("Attack Roll then hit") {
//        every { Dice.D20.roll() }.returns(15)
//        every { target.armorClass }.returns(16)
//
//        char.attackRoll(target)
//
//        verify { char["attackModifier"](allAny<Weapon>()) }
//        verify { char.proficiencyBonus }
//        verify { target.armorClass }
//    }
//
//    test("Roll Dice 1 then AttackRoll should fail") {
//        every { Dice.D20.roll() }.returns(1)
//
//        char.attackRoll(target) shouldBe false
//        verify(exactly = 0) { char["attackModifier"](allAny<Weapon>()) }
//        verify(exactly = 0) { char.proficiencyBonus }
//    }
//
//    test("Roll Dice 20 then AttackRoll should succeed") {
//        every { Dice.D20.roll() }.returns(20)
//
//        char.attackRoll(target) shouldBe true
//        verify(exactly = 0) { char["attackModifier"](allAny<Weapon>()) }
//        verify(exactly = 0) { char.proficiencyBonus }
//    }
//
//    test("Attack with Finesse or Thrown weapon uses dex modifier") {
//        every { Dice.D20.roll() } returns 10
//        every { target.armorClass } returns 13
//        val weapon = mockk<Weapon>()
//        char.equip(weapon)
//
//        listOf(Finesse, Thrown).forAll {
//            every { weapon.properties }.returns(listOf(it))
//            char.attackRoll(target)
//
//            verify { char.dexterity }
//            verify(exactly = 0) { char.strength }
//        }
//    }
//
//    test("Ranged Attack in Long range") {
//        every { Dice.D20.roll() }.returns(5)
//        every { target.armorClass }.returns(13)
//        val weapon = mockk<Weapon>()
//        every { weapon.properties }.returns(emptyList())
//        char.equip(weapon)
//
//        char.attackRoll(target, DISADVANTAGE) shouldBe false
//    }
})
