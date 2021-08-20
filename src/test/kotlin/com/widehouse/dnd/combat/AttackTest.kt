package com.widehouse.dnd.combat

import com.widehouse.dnd.character.AttackResult
import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.CharacterClass.Wizard
import com.widehouse.dnd.character.Race.Elf
import com.widehouse.dnd.character.Strength
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
        val target = Character("foo", Wizard, 2, Elf, ability = mapOf(), maxHitPoints = 20)
        every { char.attack(target) }.returns(AttackResult(target, 5))
        val result = char.attack(target)
        result.resolve()

        target.hitPoints() shouldBe 15
    }

    test("Attack Roll then hit") {
        val dice = mockk<Dice>()
        val char = Character("foo", Wizard, 3, Elf, ability = mapOf("str" to Strength(18)), maxHitPoints = 20, dice = dice)
        every { dice.roll(D20) }.returns(15)
        val target = mockk<Character>()
        every { target.armorClass() }.returns(16)

        char.attackRoll(target) shouldBe true
    }

    test("Damage Roll") {
        val sword = mockk<Weapon>()
        every { sword.damageRoll() } returns 5
        val char = Character("foo", Wizard, 2, Elf, ability = mapOf("str" to Strength(15)), maxHitPoints = 20, weapon = sword)
        char.dealDamage() shouldBe 5
    }

    test("HitPoint minimum value is zero") {
        val char = Character("foo", Wizard, 2, Elf, ability = mapOf("str" to Strength(15)), maxHitPoints = 5)
        char.getDamage(10)
        char.hitPoints() shouldBe 0
    }

    test("If get damage and hitPoint goes to 0 then character die") {
        val char = Character("foo", Wizard, 2, Elf, ability = mapOf("str" to Strength(15)), maxHitPoints = 5)
        char.getDamage(5)
        char.dead() shouldBe true
    }
})
