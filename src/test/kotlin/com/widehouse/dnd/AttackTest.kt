package com.widehouse.dnd

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AttackTest: FunSpec ({
    test("Character attack target then target get damage") {
        val char = mockk<Character>()
        val target = Character(Ability(14), 2)
        every { char.attack(target) }.returns(AttackResult(5))
        val result = char.attack(target)
        result.resolve() shouldBe 5
    }

    test("Attack Roll") {
        var char = Character(Ability(15), 2)
        char.modifier() + char.proficiency shouldBe 4
        char = Character(Ability(19), 5)
        char.modifier() + char.proficiency shouldBe 9
    }

    test("Damage Roll") {
        val sword = mockk<Weapon>()
        every { sword.damageRoll() } returns 5
        val char = Character(Ability(15), 2, weapon = sword)
        char.damage() shouldBe 5
    }
})
// TODO : Attack Roll
// TODO : hit target
// TODO : roll dice