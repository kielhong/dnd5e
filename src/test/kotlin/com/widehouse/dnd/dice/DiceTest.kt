package com.widehouse.dnd.dice

import com.widehouse.dnd.dice.Die.D10
import com.widehouse.dnd.dice.Die.D100
import com.widehouse.dnd.dice.Die.D12
import com.widehouse.dnd.dice.Die.D20
import com.widehouse.dnd.dice.Die.D4
import com.widehouse.dnd.dice.Die.D6
import com.widehouse.dnd.dice.Die.D8
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify

class DiceTest : FunSpec({
    test("Roll Dice and its result is between 1 and number(include)") {
        val dice = Dice()
        listOf(D100, D20, D12, D10, D8, D6, D4).forAll {
            dice.roll(it)
                .shouldBeGreaterThanOrEqual(1)
                .shouldBeLessThanOrEqual(it.number)
        }
    }

    test("Roll with Advantage then roll twice and get max value") {
        val dice = spyk(Dice(), recordPrivateCalls = true)
        every { dice["roll"](20) } returns 5 andThen 10

        dice.roll(D20, RollCondition.ADVANTAGE) shouldBe 10
        verify(exactly = 2) { dice["roll"](20) }
    }

    test("Roll with Disadvantage then roll twice and get min value") {
        val dice = spyk(Dice(), recordPrivateCalls = true)
        every { dice["roll"](20) } returns 5 andThen 10

        dice.roll(D20, RollCondition.DISADVANTAGE) shouldBe 5
        verify(exactly = 2) { dice["roll"](20) }
    }
})
