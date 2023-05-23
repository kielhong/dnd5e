package com.widehouse.dnd.dice

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

class DiceTest : FunSpec({
    test("Roll Dice and its result is between 1 and side") {
        Dice.values().forAll {
            it.roll()
                .shouldBeGreaterThanOrEqual(1)
                .shouldBeLessThanOrEqual(it.side)
        }
    }

//    test("Roll with Advantage then roll twice and get max value") {
//        val dice = spyk(Dice(), recordPrivateCalls = true)
//        every { dice["roll"](20) } returns 5 andThen 10
//
//        dice.roll(Dice.D20, RollCondition.ADVANTAGE) shouldBe 10
//        verify(exactly = 2) { dice["roll"](20) }
//    }
//
//    test("Roll with Disadvantage then roll twice and get min value") {
//        val dice = spyk(Dice(), recordPrivateCalls = true)
//        every { dice["roll"](20) } returns 5 andThen 10
//
//        dice.roll(Dice.D20, RollCondition.DISADVANTAGE) shouldBe 5
//        verify(exactly = 2) { dice["roll"](20) }
//    }
})
