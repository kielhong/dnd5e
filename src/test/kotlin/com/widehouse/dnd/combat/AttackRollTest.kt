package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.CharacterClass.Rogue
import com.widehouse.dnd.character.CharacterFixtures.Companion.fighter
import com.widehouse.dnd.character.Dexterity
import com.widehouse.dnd.character.Race.Halfling
import com.widehouse.dnd.character.Strength
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die
import com.widehouse.dnd.dice.Die.D20
import com.widehouse.dnd.item.Weapon
import com.widehouse.dnd.item.WeaponProperty.Finesse
import com.widehouse.dnd.item.WeaponProperty.Light
import com.widehouse.dnd.item.WeaponProperty.Thrown
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AttackRollTest : FunSpec({
    test("Attack Roll then hit") {
        val dice = mockk<Dice>()
        every { dice.roll(D20) }.returns(15)
        val target = mockk<Character>()
        every { target.armorClass() }.returns(16)
        val char = fighter(dice = dice)

        char.attackRoll(target) shouldBe true
    }

    test("Roll Dice 1 then AttackRoll should fail") {
        val dice = mockk<Dice>()
        every { dice.roll(D20) }.returns(1)
        val target = mockk<Character>()
        every { target.armorClass() }.returns(2)
        val char = fighter(dice = dice)

        char.attackRoll(target) shouldBe false
    }

    test("Roll Dice 20 then AttackRoll should succeed") {
        val dice = mockk<Dice>()
        every { dice.roll(D20) }.returns(20)
        val target = mockk<Character>()
        every { target.armorClass() }.returns(30)
        val char = fighter(dice = dice)

        char.attackRoll(target) shouldBe true
    }

    test("Attack with Finesse weapon uses dex modifier") {
        val dice = mockk<Dice>()
        every { dice.roll(D20) } returns 10 andThen 8
        val target = mockk<Character>()
        every { target.armorClass() } returns 13
        val dagger = Weapon("dagger", listOf(Die.D4), "Melee Weapon", properties = listOf(Finesse, Light, Thrown))
        val char = Character("foo", Rogue, 2, Halfling, ability = mapOf("str" to Strength(10), "dex" to Dexterity(15)), maxHitPoints = 5, weapon = dagger, dice = dice)

        char.attackRoll(target) shouldBe true
        char.attackRoll(target) shouldBe false
    }
})
