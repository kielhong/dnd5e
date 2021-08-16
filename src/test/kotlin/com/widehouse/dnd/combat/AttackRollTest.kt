package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.Strength
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die.D20
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AttackRollTest : FunSpec({
    test("Attack Roll then hit") {
        val dice = mockk<Dice>()
        val char = Character(ability = mapOf("str" to Strength(18)), 3, maxHitPoints = 20, dice = dice)
        every { dice.roll(D20) }.returns(15)
        val target = mockk<Character>()
        every { target.armorClass() }.returns(16)

        char.attackRoll(target) shouldBe true
    }

    test("Roll Dice 1 then AttackRoll should fail") {
        val dice = mockk<Dice>()
        val char = Character(ability = mapOf("str" to Strength(18)), 3, maxHitPoints = 20, dice = dice)
        every { dice.roll(D20) }.returns(1)
        val target = mockk<Character>()
        every { target.armorClass() }.returns(2)

        char.attackRoll(target) shouldBe false
    }

    test("Roll Dice 20 then AttackRoll should succeed") {
        val dice = mockk<Dice>()
        val char = Character(ability = mapOf("str" to Strength(18)), 3, maxHitPoints = 20, dice = dice)
        every { dice.roll(D20) }.returns(20)
        val target = mockk<Character>()
        every { target.armorClass() }.returns(30)

        char.attackRoll(target) shouldBe true
    }
})
