package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterFixtures.Companion.fighter
import com.widehouse.dnd.item.ItemFixtures.Companion.breastplate
import com.widehouse.dnd.item.ItemFixtures.Companion.chainMail
import com.widehouse.dnd.item.ItemFixtures.Companion.dagger
import com.widehouse.dnd.item.ItemFixtures.Companion.longSword
import com.widehouse.dnd.item.ItemFixtures.Companion.shield
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.spyk

class EquipmentTest : FunSpec({
    lateinit var char: Character

    beforeEach {
        char = spyk(fighter())
    }

    test("equip weapon") {
        char.equip(dagger)

        char.weapon shouldBe dagger
    }

    test("equip another weapon, then replace one") {
        char.equip(dagger)
        char.equip(longSword)

        char.weapon shouldBe longSword
    }

    test("equip armor") {
        char.equip(breastplate)

        char.armor shouldBe breastplate
        char.armorClass shouldBe breastplate.armorClass
    }

    test("equip another armor, replace one") {
        char.equip(breastplate)
        char.equip(chainMail)

        char.armor shouldBe chainMail
    }

    test("equip shield, then AC + 2") {
        char.equip(chainMail)
        char.equip(shield)

        char.shield shouldBe shield
        char.armorClass shouldBe chainMail.armorClass + 2
    }

    test("equip non armor, then AC is 0") {
        char.armorClass shouldBe 0
    }
})
