package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterFixtures.Companion.cleric
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify

class AbilityCheckTest : FunSpec({
    val dice = mockk<Dice>()
    val char = spyk(cleric(), recordPrivateCalls = true)

    afterEach {
        clearAllMocks()
    }

    test("Survival checks") {
        every { dice.roll(Die.D20) }.returns(14)
        every { char.wisdom }.returns(Wisdom(16))
        val challenge = Challenge(char, Survival, 15)
        val f = challenge::class.java.getDeclaredField("dice")
        f.isAccessible = true
        f.set(challenge, dice)

        challenge.result() shouldBe true
        verify { dice.roll(Die.D20) }
        verify { char.wisdom }
    }

    test("Athletic checks") {
        every { dice.roll(Die.D20) }.returns(14)
        every { char.strength }.returns(Strength(14))
        val challenge = Challenge(char, Athletics, 10)
        val f = challenge::class.java.getDeclaredField("dice")
        f.isAccessible = true
        f.set(challenge, dice)

        challenge.result() shouldBe true
        verify { dice.roll(Die.D20) }
        verify { char.strength }
    }
})