package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Die.D4
import com.widehouse.dnd.dice.Die.D6
import com.widehouse.dnd.dice.Die.D8
import com.widehouse.dnd.item.DamageType.Bludgeoning
import com.widehouse.dnd.item.DamageType.Piercing
import com.widehouse.dnd.item.DamageType.Slashing
import com.widehouse.dnd.item.WeaponCategory.Simple
import com.widehouse.dnd.item.WeaponType.Melee
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class WeaponTest : FunSpec({
    test("Weapon property") {
        val weapon = Weapon("dagger", Simple, Melee, listOf(D4), Piercing)

        weapon.name shouldBe "dagger"
        weapon.category shouldBe Simple
        weapon.type shouldBe Melee
        weapon.damageType shouldBe Piercing
    }

    test("Weapon damage roll") {
        var sword = Weapon("sword", damage = listOf(D8), damageType = Slashing)
        sword.damageRoll() shouldBeGreaterThanOrEqual 1
        sword.damageRoll() shouldBeLessThanOrEqual 8
        sword = Weapon("sword", damage = listOf(D6, D6), damageType = Bludgeoning)
        sword.damageRoll() shouldBeGreaterThanOrEqual 2
        sword.damageRoll() shouldBeLessThanOrEqual 12
    }
})
