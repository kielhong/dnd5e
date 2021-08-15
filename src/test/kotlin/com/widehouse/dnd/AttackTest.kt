package com.widehouse.dnd

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AttackTest: FunSpec ({
    test("Character attack target then target get damage") {
        val char = mockk<Character>()
        val target = Character(ability = mapOf("str" to Strength(14)), 2)
        every { char.attack(target) }.returns(AttackResult(5))
        val result = char.attack(target)
        result.resolve() shouldBe 5
    }

    test("Attack Roll then hit") {
        val dice = mockk<Dice>()
        val char = Character(ability = mapOf("str" to Strength(18)), 3, dice = dice)
        every { dice.roll(20) }.returns(15)
        val target = mockk<Character>()
        every { target.armorClass() }.returns(16)

        char.attackRoll(target) shouldBe true
    }

    test("Damage Roll") {
        val sword = mockk<Weapon>()
        every { sword.damageRoll() } returns 5
        val char = Character(ability = mapOf("str" to Strength(15)), 2, weapon = sword)
        char.damage() shouldBe 5
    }
})
