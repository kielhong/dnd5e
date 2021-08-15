package com.widehouse.dnd

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ArmorTest: FunSpec ({
    test("Light Armor, add dex modifier to base armor class") {
        // Light Armor, add dex modifier
        val armor = Armor(name = "Padded", category = "Light Armor", ac = 11)
        val char = Character(ability = mapOf("dex" to Dexterity(14)), 2, armor = armor)
        char.armorClass() shouldBe armor.ac + char.ability["dex"]!!.modifier()
    }

    test("Medium Armor, add dex modifier to base armor class") {
        val armor = Armor(name = "Hide", category = "Medium Armor", ac = 12)
        val char = Character(ability = mapOf("dex" to Dexterity(18)), 2, armor = armor)
        char.armorClass() shouldBe armor.ac + 2
    }

    test("Heavy Armor, does not add dex modifier to base armor class") {
        val armor = Armor(name = "Chain Main", category = "Heavy Armor", ac = 16)
        val char = Character(ability = mapOf("dex" to Dexterity(18)), 2, armor = armor)
        char.armorClass() shouldBe armor.ac
    }
})