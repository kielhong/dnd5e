package com.widehouse.dnd.character

import com.widehouse.dnd.Strength
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die.D20
import com.widehouse.dnd.item.Weapon
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AttackTest : FunSpec({
    test("Character attack target then target get damage") {
        val char = mockk<Character>()
        val target = Character(ability = mapOf(), 2, maxHitPoints = 20)
        every { char.attack(target) }.returns(AttackResult(target, 5))
        val result = char.attack(target)
        result.resolve()

        target.hitPoints() shouldBe 15
    }

    test("Attack Roll then hit") {
        val dice = mockk<Dice>()
        val char = Character(ability = mapOf("str" to Strength(18)), 3, maxHitPoints = 20, dice = dice)
        every { dice.roll(D20) }.returns(15)
        val target = mockk<Character>()
        every { target.armorClass() }.returns(16)

        char.attackRoll(target) shouldBe true
    }

    test("Damage Roll") {
        val sword = mockk<Weapon>()
        every { sword.damageRoll() } returns 5
        val char = Character(ability = mapOf("str" to Strength(15)), 2, maxHitPoints = 20, weapon = sword)
        char.dealDamage() shouldBe 5
    }
})
