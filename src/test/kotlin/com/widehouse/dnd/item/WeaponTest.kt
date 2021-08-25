package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Die.D4
import com.widehouse.dnd.dice.Die.D6
import com.widehouse.dnd.dice.Die.D8
import com.widehouse.dnd.item.DamageType.Bludgeoning
import com.widehouse.dnd.item.DamageType.Piercing
import com.widehouse.dnd.item.DamageType.Slashing
import com.widehouse.dnd.item.ItemFixtures.Companion.longBow
import com.widehouse.dnd.item.WeaponCategory.Simple
import com.widehouse.dnd.item.WeaponType.Melee
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class WeaponTest : FunSpec({
    test("Weapon property") {
        val weapon = Weapon("dagger", Simple, Melee, listOf(D4), Piercing, cost = Coin(2, GP), weight = 1)
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
        var sword = Weapon("sword", damage = listOf(D8), damageType = Slashing)
        sword.damageRoll() shouldBeGreaterThanOrEqual 1
        sword.damageRoll() shouldBeLessThanOrEqual 8
        sword = Weapon("sword", damage = listOf(D6, D6), damageType = Bludgeoning)
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
