package com.widehouse.dnd.item

import com.widehouse.dnd.character.CharacterFixtures
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ArmorTest : FunSpec({
    test("Light Armor, add dex modifier to base armor class") {
        val armor = ItemFixtures.padded
        val char = CharacterFixtures.rogue()
        char.equip(armor)
        char.armorClass shouldBe armor.armorClass + char.dexterity.modifier()
    }

    test("Medium Armor, add dex modifier to base armor class") {
        val armor = ItemFixtures.breastplate
        val char = CharacterFixtures.rogue()
        char.equip(armor)
        char.armorClass shouldBe armor.armorClass + 2
    }

    test("Heavy Armor, does not add dex modifier to base armor class") {
        val armor = ItemFixtures.chainMail
        val char = CharacterFixtures.rogue()
        char.equip(armor)
        char.armorClass shouldBe armor.armorClass
    }
})
