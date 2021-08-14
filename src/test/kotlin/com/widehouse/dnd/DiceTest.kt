package com.widehouse.dnd

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

class DiceTest: FunSpec({
    test("Roll Dice 20") {
        val dice = Dice()
        listOf(100, 20, 12, 10, 8, 6, 4).forAll {
            dice.roll(it) shouldBeGreaterThanOrEqual 1
            dice.roll(it) shouldBeLessThanOrEqual it
        }
    }
})