package com.widehouse.dnd.item

import com.widehouse.dnd.character.PlayerCharacterFixtures
import com.widehouse.dnd.character.player.PlayerCharacter
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ArmorTest : FunSpec({
    lateinit var character: PlayerCharacter

    beforeEach {
        character = PlayerCharacterFixtures.rogue
    }

    test("Shield item test") {
        val shield = ItemFixtures.shield
        assertSoftly(shield) {
            it.name shouldBe "Shield"
            it.armorClass shouldBe 2
            it.cost shouldBe Coin(10, GP)
            it.weight shouldBe 6
        }
    }

    test("Light Armor, add dex modifier to base armor class") {
        val armor = ItemFixtures.padded
        character.equipment.armor = armor

        character.armorClass shouldBe armor.armorClass + character.abilities.dexterity.modifier
    }

    test("Medium Armor, add dex modifier to base armor class") {
        val armor = ItemFixtures.breastplate
        character.equipment.armor = armor

        character.armorClass shouldBe armor.armorClass + 2
    }

    test("Heavy Armor, does not add dex modifier to base armor class") {
        val armor = ItemFixtures.chainMail
        character.equipment.armor = ItemFixtures.chainMail

        character.armorClass shouldBe armor.armorClass
    }

    test("Equip Shield, add 2 AC") {
        val armor = ItemFixtures.chainMail
        val shield = ItemFixtures.shield
        character.equipment.armor = armor
        character.equipment.shield = shield

        character.armorClass shouldBe armor.armorClass + shield.armorClass
    }

    test("Padded Armor cost is 5gp, weight 8 lb") {
        assertSoftly(ItemFixtures.padded) {
            name shouldBe "Padded Armor"
            cost shouldBe Coin(5, GP)
            weight shouldBe 8
        }
    }
})
