package com.widehouse.dnd.dice

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

class DiceTest : FunSpec({
    test("Roll Dice 20") {
        val dice = Dice()
        listOf(100, 20, 12, 10, 8, 6, 4).forAll {
            dice.roll(it)
                .shouldBeGreaterThanOrEqual(1)
                .shouldBeLessThanOrEqual(it)
        }
    }

    test("Roll with Advantage then roll twice and get max value") {
        val dice = Dice()
        dice.roll(20, RollCondition.ADVANTAGE)
            .shouldBeGreaterThanOrEqual(1)
            .shouldBeLessThanOrEqual(20)
    }

    test("Roll with Disadvantage then roll twice and get min value") {
        val dice = Dice()
        dice.roll(20, RollCondition.DISADVANTAGE)
            .shouldBeGreaterThanOrEqual(1)
            .shouldBeLessThanOrEqual(20)
    }
})
