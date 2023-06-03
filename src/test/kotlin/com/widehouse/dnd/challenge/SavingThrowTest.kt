package com.widehouse.dnd.challenge

import com.widehouse.dnd.dice.RollSituation
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class SavingThrowTest : FunSpec({
    val rollSituation = mockk<RollSituation>()

    beforeEach {
        clearAllMocks()
    }

    test("Saving Throw") {
        every { rollSituation.roll() } returns 14
        val savingThrow = SavingThrow(rollSituation, listOf(2, 2), 15)

        savingThrow.challenge() shouldBe true
    }

    test("Saving Throw with modifiers") {
        every { rollSituation.roll() } returns 14
        val savingThrow = SavingThrow(rollSituation, listOf(2, 2), 15)

        savingThrow.challenge() shouldBe true
    }
})
