package com.widehouse.dnd.character.action

import com.widehouse.dnd.character.PlayerCharacter
import com.widehouse.dnd.character.PlayerCharacterFixtures.cleric
import com.widehouse.dnd.character.PlayerCharacterFixtures.fighter
import com.widehouse.dnd.character.PlayerCharacterFixtures.rogue
import com.widehouse.dnd.character.PlayerCharacterFixtures.wizard
import com.widehouse.dnd.item.Weapon
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk

class AttackTest : FunSpec({
//    test("Character attack target then target get damage") {
//        val char = mockk<PlayerCharacter>()
//        val target = spyk(wizard)
//        every { char.attack(target) }.returns(AttackResult(target, 5))
//        val result = char.attack(target)
//        result.resolve()
//
//        target.hitPoints shouldBe 3
//    }

    test("Damage Roll") {
        val sword = mockk<Weapon>()
        every { sword.damageRoll() } returns 5
        val char = fighter
        char.equip(sword)
        char.dealDamage() shouldBe 5
    }

    test("HitPoint minimum value is zero") {
        val char = rogue
        char.getDamage(20)
        char.hitPoints shouldBe 0
    }

    test("If get damage and hitPoint goes to 0 then character die") {
        val char = cleric
        char.getDamage(20)
        char.dead() shouldBe true
    }
})
