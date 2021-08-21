package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterFixtures.Companion.fighter
import com.widehouse.dnd.character.CharacterFixtures.Companion.rogue
import com.widehouse.dnd.item.ItemFixtures.Companion.breastplate
import com.widehouse.dnd.item.ItemFixtures.Companion.chainMail
import com.widehouse.dnd.item.ItemFixtures.Companion.dagger
import com.widehouse.dnd.item.ItemFixtures.Companion.longSword
import com.widehouse.dnd.item.ItemFixtures.Companion.shield
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class EquipmentTest : FunSpec({
    test("equip weapon") {
        val char = fighter()
        char.equip(dagger)

        char.weapon shouldBe dagger
    }

    test("equip another weapon, then replace one") {
        val char = fighter()
        char.equip(dagger)
        char.equip(longSword)

        char.weapon shouldBe longSword
    }

    test("equip armor") {
        val char = fighter()
        char.equip(breastplate)

        char.armor shouldBe breastplate
        char.armorClass shouldBe breastplate.armorClass
    }

    test("equip another armor, replace one") {
        val char = fighter()
        char.equip(breastplate)
        char.equip(chainMail)

        char.armor shouldBe chainMail
    }

    test("equip shield, then AC + 2") {
        val char = rogue()
        char.equip(chainMail)
        char.equip(shield)

        char.shield shouldBe shield
        char.armorClass shouldBe chainMail.armorClass + 2
    }
})
