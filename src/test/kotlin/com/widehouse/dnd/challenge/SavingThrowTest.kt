package com.widehouse.dnd.challenge

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.RollCondition
import com.widehouse.dnd.dice.RollSituation
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class SavingThrowTest : FunSpec({
    val dice = mockk<Dice>()

    beforeEach {
        clearAllMocks()
    }

    test("Saving Throw") {
        every { dice.roll() }.returns(14)
        val rollSituation = RollSituation.of(dice)
        val savingThrow = SavingThrow(rollSituation, listOf(2, 2), 15)

        savingThrow.result() shouldBe true
    }

    test("Saving Throw - advantage") {
        val dice1 = mockk<Dice>() {
            every { roll() } returns 14
        }
        val dice2 = mockk<Dice>() {
            every { roll() } returns 10
        }
        val rollSituation = RollSituation.of(listOf(dice1, dice2), RollCondition.ADVANTAGE)
        val savingThrow = SavingThrow(rollSituation, listOf(2, 2), 15)

        savingThrow.result() shouldBe true
    }

    test("Saving Throw - disadvantage") {
        val dice1 = mockk<Dice>() {
            every { roll() } returns 14
        }
        val dice2 = mockk<Dice>() {
            every { roll() } returns 10
        }
        val rollSituation = RollSituation.of(dice1, dice2, condition = RollCondition.DISADVANTAGE)
        val savingThrow = SavingThrow(rollSituation, listOf(2, 2), 15)

        savingThrow.result() shouldBe false
    }

    test("when roll without dice throw error") {
        val rollSituation = RollSituation.of(listOf())
        val savingThrow = SavingThrow(rollSituation, listOf(), 15)

        shouldThrow<IllegalArgumentException> {
            savingThrow.result()
        }
    }
})
