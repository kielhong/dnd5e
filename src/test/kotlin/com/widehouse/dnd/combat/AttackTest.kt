package com.widehouse.dnd.combat

import com.widehouse.dnd.character.AttackResult
import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.CharacterFixtures.Companion.cleric
import com.widehouse.dnd.character.CharacterFixtures.Companion.fighter
import com.widehouse.dnd.character.CharacterFixtures.Companion.rogue
import com.widehouse.dnd.character.CharacterFixtures.Companion.wizard
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
        val target = wizard()
        every { char.attack(target) }.returns(AttackResult(target, 5))
        val result = char.attack(target)
        result.resolve()

        target.hitPoints shouldBe 15
    }

    test("Attack Roll then hit") {
        val dice = mockk<Dice>()
        every { dice.roll(D20) }.returns(15)
        val target = mockk<Character>()
        every { target.armorClass }.returns(16)
        val char = fighter(dice = dice)

        char.attackRoll(target) shouldBe true
    }

    test("Damage Roll") {
        val sword = mockk<Weapon>()
        every { sword.damageRoll() } returns 5
        val char = fighter()
        char.equip(sword)
        char.dealDamage() shouldBe 5
    }

    test("HitPoint minimum value is zero") {
        val char = rogue(hp = 5)
        char.getDamage(10)
        char.hitPoints shouldBe 0
    }

    test("If get damage and hitPoint goes to 0 then character die") {
        val char = cleric(hp = 5)
        char.getDamage(5)
        char.dead() shouldBe true
    }
})
