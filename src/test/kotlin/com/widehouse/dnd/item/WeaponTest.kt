package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.item.DamageType.Piercing
import com.widehouse.dnd.item.DamageType.Slashing
import com.widehouse.dnd.item.ItemFixtures.longBow
import com.widehouse.dnd.item.WeaponCategory.Simple
import com.widehouse.dnd.item.WeaponType.Melee
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class WeaponTest : FunSpec({
    test("Weapon property") {
        val weapon = Weapon("dagger", cost = Coin(2, GP), weight = 1, Simple, Melee, listOf(Dice.D4), Piercing)
        assertSoftly(weapon) {
            name shouldBe "dagger"
            category shouldBe Simple
            type shouldBe Melee
            damageType shouldBe Piercing
            cost shouldBe Coin(2, GP)
            weight shouldBe 1
        }
    }

    test("Weapon damage roll") {
        var sword = Weapon("sword", damage = listOf(Dice.D8), damageType = Slashing)
        sword.damageRoll() shouldBeGreaterThanOrEqual 1
        sword.damageRoll() shouldBeLessThanOrEqual 8
        sword = Weapon("sword", damage = listOf(Dice.D6, Dice.D6), damageType = DamageType.Bludgeoning)
        sword.damageRoll() shouldBeGreaterThanOrEqual 2
        sword.damageRoll() shouldBeLessThanOrEqual 12
    }

    test("Range Weapon in Normal Range") {
        val weapon = longBow
        weapon.damageRoll()
            .shouldBeGreaterThanOrEqual(1)
            .shouldBeLessThanOrEqual(8)
    }
})
