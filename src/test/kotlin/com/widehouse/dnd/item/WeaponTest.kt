package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Die.D6
import com.widehouse.dnd.dice.Die.D8
import com.widehouse.dnd.item.Weapon
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

class WeaponTest : FunSpec({
    test("Weapon damage roll") {
        var sword = Weapon("sword", listOf(D8), "Melee Weapon")
        sword.damageRoll() shouldBeGreaterThanOrEqual 1
        sword.damageRoll() shouldBeLessThanOrEqual 8
        sword = Weapon("sword", listOf(D6, D6), "Melee Weapon")
        sword.damageRoll() shouldBeGreaterThanOrEqual 2
        sword.damageRoll() shouldBeLessThanOrEqual 12
    }
})
