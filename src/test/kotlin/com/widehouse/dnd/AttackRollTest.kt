package com.widehouse.dnd

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AttackRollTest: FunSpec ({
    test("Attack Roll then hit") {
        val dice = mockk<Dice>()
        val char = Character(Ability(18), 3, dice = dice)
        every { dice.roll(20) }.returns(15)
        val target = mockk<Character>()
        every { target.getArmorClass() }.returns(16)

        char.attackRoll(target) shouldBe true
    }

    test("Roll Dice 1 then AttackRoll should fail") {
        val dice = mockk<Dice>()
        val char = Character(Ability(18), 3, dice = dice)
        every { dice.roll(20) }.returns(1)
        val target = mockk<Character>()
        every { target.getArmorClass() }.returns(2)

        char.attackRoll(target) shouldBe false
    }

    test("Roll Dice 20 then AttackRoll should succeed") {
        val dice = mockk<Dice>()
        val char = Character(Ability(18), 3, dice = dice)
        every { dice.roll(20) }.returns(20)
        val target = mockk<Character>()
        every { target.getArmorClass() }.returns(30)

        char.attackRoll(target) shouldBe true
    }
})