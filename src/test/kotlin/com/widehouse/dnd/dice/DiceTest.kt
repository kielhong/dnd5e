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
})
