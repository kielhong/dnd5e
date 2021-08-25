package com.widehouse.dnd.combat

import com.widehouse.dnd.character.AbilityType
import com.widehouse.dnd.character.CharacterFixtures.Companion.wizard
import com.widehouse.dnd.character.Constitution
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify

class SavingThrowTest : FunSpec({
    val dice = mockk<Dice>()
    val char = spyk(wizard(), recordPrivateCalls = true)

    afterEach {
        clearAllMocks()
    }

    test("test Saving Throw with CON") {
        every { dice.roll(Die.D20) }.returns(14)
        every { char.constitution }.returns(Constitution(16))
        val savingThrow = SavingThrow(char, AbilityType.Constitution, 15)
        val f = savingThrow::class.java.getDeclaredField("dice")
        f.isAccessible = true
        f.set(savingThrow, dice)

        savingThrow.result() shouldBe true
        verify { dice.roll(Die.D20) }
        verify { char.constitution }
    }
})