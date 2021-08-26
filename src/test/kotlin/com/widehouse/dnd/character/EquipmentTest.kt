package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterFixtures.Companion.fighter
import com.widehouse.dnd.item.Coin
import com.widehouse.dnd.item.ItemFixtures.Companion.breastplate
import com.widehouse.dnd.item.ItemFixtures.Companion.chainMail
import com.widehouse.dnd.item.ItemFixtures.Companion.dagger
import com.widehouse.dnd.item.ItemFixtures.Companion.longBow
import com.widehouse.dnd.item.ItemFixtures.Companion.longSword
import com.widehouse.dnd.item.ItemFixtures.Companion.shield
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.mockk.spyk

class EquipmentTest : FunSpec({
    lateinit var char: Character

    beforeEach {
        char = spyk(fighter())
        char.getItem(dagger)
        char.getItem(longSword)
        char.getItem(breastplate)
        char.getItem(chainMail)
        char.getItem(shield)
    }

    test("equip weapon in inventory") {
        char.equip(dagger)

        assertSoftly(char) {
            weapon shouldBe dagger
            inventory shouldNotContain dagger
        }
    }

    test("equip another weapon, then unequip previous one") {
        char.equip(dagger)
        char.equip(longSword)

        assertSoftly(char) {
            weapon shouldBe longSword
            inventory shouldContain dagger
            inventory shouldNotContain longSword
        }
    }

    test("equip armor") {
        char.equip(breastplate)

        assertSoftly(char) {
            armor shouldBe breastplate
            inventory shouldNotContain breastplate
            armorClass shouldBe breastplate.armorClass
        }
    }

    test("equip another armor, replace one") {
        char.equip(breastplate)
        char.equip(chainMail)

        assertSoftly(char) {
            armor shouldBe chainMail
            inventory shouldContain breastplate
            inventory shouldNotContain chainMail
        }
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

    test("add Coin then add coin") {
        char.coin += Coin(100)

        char.coin shouldBe Coin(100)
    }

    test("subtract Coin then loose coin") {
        char.coin = Coin(100)
        char.coin -= Coin(50)

        char.coin shouldBe Coin(50)
    }

    test("get item") {
        val item = longBow
        char.getItem(item)

        char.inventory shouldContain item
    }

    test("drop item") {
        val item = longBow
        char.getItem(item)
        char.dropItem(item)

        char.inventory shouldNotContain item
    }
})
