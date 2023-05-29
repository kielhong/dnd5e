package com.widehouse.dnd.character.item

import com.widehouse.dnd.character.PlayerCharacterFixtures
import com.widehouse.dnd.character.player.PlayerCharacter
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ArmorTest : FunSpec({
    lateinit var character: PlayerCharacter

    beforeEach {
        character = PlayerCharacterFixtures.rogue.apply {
            equipment.armor = null
            equipment.shield = null
        }
    }

    test("Light Armor, add dex modifier to base armor class") {
        val armor = mockk<Armor>()
        every { armor.armorClass } returns 2
        every { armor.itemType } returns ArmorType.LightArmor

        character.equipment.armor = armor

        character.armorClass shouldBe armor.armorClass + character.abilities.dexterity.modifier
    }

    test("Medium Armor, add dex modifier to base armor class") {
        val armor = mockk<Armor>()
        every { armor.armorClass } returns 2
        every { armor.itemType } returns ArmorType.MediumArmor

        character.equipment.armor = armor

        character.armorClass shouldBe armor.armorClass + 2
    }

    test("Heavy Armor, does not add dex modifier to base armor class") {
        val armor = mockk<Armor>()
        every { armor.armorClass } returns 2
        every { armor.itemType } returns ArmorType.HeavyArmor

        character.equipment.armor = armor

        character.armorClass shouldBe armor.armorClass
    }

    test("Equip Shield, add 2 AC") {
        val armor = mockk<Armor>()
        every { armor.armorClass } returns 5
        every { armor.itemType } returns ArmorType.HeavyArmor
        val shield = mockk<Shield>()
        every { shield.armorClass } returns 2

        character.equipment.armor = armor
        character.equipment.shield = shield

        character.armorClass shouldBe armor.armorClass + shield.armorClass
    }

    test("no Armor, no Shield then 0 AC") {
        character.equipment.armor = null
        character.equipment.shield = null

        character.armorClass shouldBe 0
    }

    test("Padded Armor cost is 5gp, weight 8 lb") {
        assertSoftly(Armor("Padded Armor", ArmorType.LightArmor, 11, Coin(5, GP), 8)) {
            name shouldBe "Padded Armor"
            cost shouldBe Coin(5, GP)
            weight shouldBe 8
            armorClass shouldBe 11
            itemType shouldBe ArmorType.LightArmor
        }
    }

    test("Shield item test") {
        assertSoftly(Shield("Shield", 2, Coin(10, GP), 6)) {
            it.name shouldBe "Shield"
            it.armorClass shouldBe 2
            it.cost shouldBe Coin(10, com.widehouse.dnd.character.item.GP)
            it.weight shouldBe 6
        }
    }
})
