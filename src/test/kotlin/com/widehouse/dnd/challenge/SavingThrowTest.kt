package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.AbilityType
import com.widehouse.dnd.character.CharacterFixtures.wizard
import com.widehouse.dnd.character.Constitution
import com.widehouse.dnd.character.Dexterity
import com.widehouse.dnd.dice.Dice
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class SavingThrowTest : FunSpec({
    val dice = mockk<Dice>()
    val char = spyk(wizard(), recordPrivateCalls = true)

    afterEach {
        clearAllMocks()
    }

//    test("test Saving Throw with proficiency ability") {
//        every { Dice.D20.roll() }.returns(14)
//        every { char.constitution }.returns(Constitution(16))
//        every { char.proficiencyBonus }.returns(2)
//        every { char.proficiencySavingThrow }.returns(listOf(AbilityType.Intelligence, AbilityType.Constitution))
//        val savingThrow = SavingThrow(char, AbilityType.Constitution, 15)
//        val f = savingThrow::class.java.getDeclaredField("dice")
//        f.isAccessible = true
//        f.set(savingThrow, dice)
//
//        savingThrow.result() shouldBe true
//        verify { Dice.D20.roll() }
//        verify { char.constitution }
//        verify { char.proficiencyBonus }
//    }
//
//    test("test Saving Throw without proficiency ability") {
//        every { Dice.D20.roll() }.returns(10)
//        every { char.dexterity }.returns(Dexterity(10))
//        every { char.proficiencyBonus }.returns(2)
//        every { char.proficiencySavingThrow }.returns(listOf(AbilityType.Intelligence, AbilityType.Constitution))
//        val savingThrow = SavingThrow(char, AbilityType.Dexterity, 15)
//        val f = savingThrow::class.java.getDeclaredField("dice")
//        f.isAccessible = true
//        f.set(savingThrow, dice)
//
//        savingThrow.result() shouldBe false
//        verify { Dice.D20.roll() }
//        verify { char.dexterity }
//        verify(exactly = 0) { char.proficiencyBonus }
//    }
})
