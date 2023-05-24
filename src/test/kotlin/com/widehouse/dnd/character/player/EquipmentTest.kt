package com.widehouse.dnd.character.player

import com.widehouse.dnd.character.PlayerCharacterFixtures.fighter
import com.widehouse.dnd.item.ItemFixtures.breastplate
import com.widehouse.dnd.item.ItemFixtures.chainMail
import com.widehouse.dnd.item.ItemFixtures.dagger
import com.widehouse.dnd.item.ItemFixtures.longSword
import com.widehouse.dnd.item.ItemFixtures.ring
import com.widehouse.dnd.item.ItemFixtures.shield
import io.kotest.core.spec.style.FunSpec
import io.mockk.clearAllMocks

class EquipmentTest : FunSpec({
    lateinit var char: PlayerCharacter

    beforeEach {
        char = fighter
        char.getItem(dagger)
        char.getItem(longSword)
        char.getItem(breastplate)
        char.getItem(chainMail)
        char.getItem(shield)
        char.getItem(ring)
    }

    afterEach {
        clearAllMocks()
    }

//    test("equip weapon in inventory") {
//        char.equip(dagger)
//
//        assertSoftly(char) {
//            equipment.mainHand shouldBe dagger
//            inventory shouldNotContain dagger
//        }
//    }
//
//    test("equip another weapon, then unequip previous one") {
//        char.equip(dagger)
//        char.equip(longSword)
//
//        assertSoftly(char) {
//            equipment.mainHand shouldBe longSword
//            inventory shouldContain dagger
//            inventory shouldNotContain longSword
//        }
//    }
//
//    test("equip armor") {
//        char.equip(breastplate)
//
//        assertSoftly(char) {
//            equipment.armor shouldBe breastplate
//            inventory shouldNotContain breastplate
//            armorClass shouldBe breastplate.armorClass
//        }
//    }
//
//    test("equip another armor, replace one") {
//        char.equip(breastplate)
//        char.equip(chainMail)
//
//        assertSoftly(char) {
//            equipment.armor shouldBe chainMail
//            inventory shouldContain breastplate
//            inventory shouldNotContain chainMail
//        }
//    }
//
//    test("equip shield, then AC + 2") {
//        char.equip(chainMail)
//        char.equip(shield)
//
//        assertSoftly(char) {
//            equipment.offHand shouldBe shield
//            inventory shouldNotContain shield
//            armorClass shouldBe chainMail.armorClass + 2
//        }
//    }
//
//    test("equip ring to accessory") {
//        char.equip(ring)
//
//        char.equipment.accessory shouldContain ring
//        char.inventory shouldNotContain ring
//    }
//
//    test("equip non armor, then AC is 0") {
//        char.armorClass shouldBe 0
//    }
//
//    test("unequip item") {
//        char.equip(dagger)
//        char.unequip(dagger)
//        char.equipment.mainHand shouldBe null
//
//        char.equip(shield)
//        char.unequip(shield)
//        char.equipment.offHand shouldBe null
//
//        char.equip(chainMail)
//        char.unequip(chainMail)
//        char.equipment.armor shouldBe null
//
//        char.equip(ring)
//        char.unequip(ring)
//        char.equipment.accessory shouldNotContain ring
//    }
//
//    test("add Coin then add coin") {
//        char.coin += Coin(100)
//
//        char.coin shouldBe Coin(100)
//    }
//
//    test("subtract Coin then loose coin") {
//        char.coin = Coin(100)
//        char.coin -= Coin(50)
//
//        char.coin shouldBe Coin(50)
//    }
//
//    test("get item") {
//        val item = longBow
//        char.getItem(item)
//
//        char.inventory shouldContain item
//    }
//
//    test("drop item") {
//        val item = longBow
//        char.getItem(item)
//        char.dropItem(item)
//
//        char.inventory shouldNotContain item
//    }
})
