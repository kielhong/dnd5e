package com.widehouse.dnd.item

import com.widehouse.dnd.character.Abilities
import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.CharacterClass.Rogue
import com.widehouse.dnd.character.Race.Halfling
import com.widehouse.dnd.item.ArmorType.HeavyArmor
import com.widehouse.dnd.item.ArmorType.LightArmor
import com.widehouse.dnd.item.ArmorType.MediumArmor
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ArmorTest : FunSpec({
    test("Light Armor, add dex modifier to base armor class") {
        val armor = Armor(name = "Padded", armorType = LightArmor, armorClass = 11)
        val char = Character("foo", Rogue, 2, Halfling, abilities = Abilities(12, 14, 12, 12, 12, 12), maxHitPoints = 20, armor = armor)
        char.armorClass() shouldBe armor.armorClass + char.dexterity.modifier()
    }

    test("Medium Armor, add dex modifier to base armor class") {
        val armor = Armor(name = "Hide", armorType = MediumArmor, armorClass = 12)
        val char = Character("foo", Rogue, 2, Halfling, abilities = Abilities(12, 14, 12, 12, 12, 12), maxHitPoints = 20, armor = armor)
        char.armorClass() shouldBe armor.armorClass + 2
    }

    test("Heavy Armor, does not add dex modifier to base armor class") {
        val armor = Armor(name = "Chain Main", armorType = HeavyArmor, armorClass = 16)
        val char = Character("foo", Rogue, 2, Halfling, abilities = Abilities(12, 18, 12, 12, 12, 12), maxHitPoints = 20, armor = armor)
        char.armorClass() shouldBe armor.armorClass
    }
})
