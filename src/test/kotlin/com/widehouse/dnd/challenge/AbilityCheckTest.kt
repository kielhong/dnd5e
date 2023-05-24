package com.widehouse.dnd.challenge

import com.widehouse.dnd.dice.Dice
import io.kotest.core.spec.style.FunSpec
import io.mockk.clearAllMocks
import io.mockk.mockk

class AbilityCheckTest : FunSpec({
    val dice = mockk<Dice>()

    afterEach {
        clearAllMocks()
    }

//    test("Survival checks") {
//        every { Dice.D20.roll() }.returns(14)
//        every { char.wisdom }.returns(Wisdom(16))
//        val challenge = AbilityCheck(char, Survival, 15)
//        val f = challenge::class.java.getDeclaredField("dice")
//        f.isAccessible = true
//        f.set(challenge, dice)
//
//        challenge.result() shouldBe true
//        verify { Dice.D20.roll() }
//        verify { char.wisdom }
//    }
//
//    test("Athletic checks with proficiency skill") {
//        every { Dice.D20.roll() }.returns(14)
//        every { char.strength }.returns(Strength(14))
//        every { char.proficiencySkill }.returns(listOf(Athletics, History))
//        val challenge = AbilityCheck(char, Athletics, 10)
//        val f = challenge::class.java.getDeclaredField("dice")
//        f.isAccessible = true
//        f.set(challenge, dice)
//
//        challenge.result() shouldBe true
//        verify { Dice.D20.roll() }
//        verify { char.strength }
//        verify { char.proficiencyBonus }
//    }
//
//    test("Athletic checks without proficiency skill") {
//        every { Dice.D20.roll() }.returns(5)
//        every { char.dexterity }.returns(Dexterity(14))
//        every { char.proficiencySkill }.returns(listOf(Athletics, History))
//        val challenge = AbilityCheck(char, Acrobatics, 15)
//        val f = challenge::class.java.getDeclaredField("dice")
//        f.isAccessible = true
//        f.set(challenge, dice)
//
//        challenge.result() shouldBe false
//        verify { Dice.D20.roll() }
//        verify { char.dexterity }
//        verify(exactly = 0) { char.proficiencyBonus }
//    }
})
