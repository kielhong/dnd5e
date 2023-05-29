package com.widehouse.dnd.character.player

import com.widehouse.dnd.character.PlayerCharacterFixtures.fighter
import com.widehouse.dnd.character.item.Coin
import com.widehouse.dnd.item.ItemFixtures.breastplate
import com.widehouse.dnd.item.ItemFixtures.chainMail
import com.widehouse.dnd.item.ItemFixtures.dagger
import com.widehouse.dnd.item.ItemFixtures.longBow
import com.widehouse.dnd.item.ItemFixtures.longSword
import com.widehouse.dnd.item.ItemFixtures.ring
import com.widehouse.dnd.item.ItemFixtures.shield
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks

class EquipmentTest : FunSpec({
    lateinit var character: PlayerCharacter

    beforeEach {
        character = fighter
        character.getItem(dagger)
        character.getItem(longSword)
        character.getItem(breastplate)
        character.getItem(chainMail)
        character.getItem(shield)
        character.getItem(ring)
    }

    afterEach {
        clearAllMocks()
    }

    test("add Coin then add coin") {
        character.coin += Coin(100)

        character.coin shouldBe Coin(100)
    }

    test("subtract Coin then loose coin") {
        character.coin = Coin(100)
        character.coin -= Coin(50)

        character.coin shouldBe Coin(50)
    }

    test("get item") {
        // given
        val item = longBow
        character.equipment.items.clear()

        character.getItem(item)

        character.equipment.items shouldContain item
    }

    test("drop item") {
        // given
        val item = longBow
        character.equipment.items.clear()
        character.equipment.items.add(item)

        character.dropItem(item)

        character.equipment.items shouldNotContain item
    }
})
