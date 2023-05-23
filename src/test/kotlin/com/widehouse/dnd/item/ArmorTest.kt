package com.widehouse.dnd.item

import com.widehouse.dnd.character.CharacterOld
import com.widehouse.dnd.character.CharacterFixtures.Companion.rogue
import com.widehouse.dnd.item.ItemFixtures.Companion.breastplate
import com.widehouse.dnd.item.ItemFixtures.Companion.chainMail
import com.widehouse.dnd.item.ItemFixtures.Companion.padded
import com.widehouse.dnd.item.ItemFixtures.Companion.shield
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.spyk

class ArmorTest : FunSpec({
    lateinit var char: CharacterOld

    beforeEach {
        char = spyk(rogue())
    }

    test("Shield item test") {
        val shield = shield
        assertSoftly(shield) {
            it.name shouldBe "Shield"
            it.armorClass shouldBe 2
            it.cost shouldBe Coin(10, GP)
            it.weight shouldBe 6
        }
    }

    test("Light Armor, add dex modifier to base armor class") {
        val armor = padded
        char.equip(armor)
        char.armorClass shouldBe armor.armorClass + char.dexterity.modifier
    }

    test("Medium Armor, add dex modifier to base armor class") {
        val armor = breastplate
        char.equip(armor)
        char.armorClass shouldBe armor.armorClass + 2
    }

    test("Heavy Armor, does not add dex modifier to base armor class") {
        val armor = chainMail
        char.equip(armor)
        char.armorClass shouldBe armor.armorClass
    }

    test("Equip Shield, add 2 AC") {
        val armor = chainMail
        val shield = shield
        char.equip(armor)
        char.equip(shield)
        char.armorClass shouldBe armor.armorClass + shield.armorClass
    }

    test("Padded Armor cost is 5gp, weight 8 lb") {
        assertSoftly(padded) {
            name shouldBe "Padded Armor"
            cost shouldBe Coin(5, GP)
            weight shouldBe 8
        }
    }
})
