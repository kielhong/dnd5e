package com.widehouse.dnd.dice

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class RollSituationTest : FunSpec({
    test("roll one dice") {
        val dice = mockk<Dice> {
            every { roll() } returns 6
        }
        val rollSituation = RollSituation.of(dice)

        rollSituation.roll() shouldBe 6
    }

    test("Advantage when roll then bigger dice roll") {
        val dice1 = mockk<Dice> { every { roll() } returns 6 }
        val dice2 = mockk<Dice> { every { roll() } returns 12 }
        val rollSituation = RollSituation.of(listOf(dice1, dice2), RollCondition.ADVANTAGE)

        rollSituation.roll() shouldBe 12
    }

    test("Disadvantage when roll then smaller dice roll") {
        val dice1 = mockk<Dice> { every { roll() } returns 6 }
        val dice2 = mockk<Dice> { every { roll() } returns 12 }
        val rollSituation = RollSituation.of(listOf(dice1, dice2), RollCondition.DISADVANTAGE)

        rollSituation.roll() shouldBe 6
    }

    test("given no dice when roll then exception") {
        val rollSituation = RollSituation.of(listOf())

        shouldThrow<IllegalArgumentException> {
            rollSituation.roll()
        }
    }
})
