package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterFixtures.Companion.fighter
import com.widehouse.dnd.item.ItemFixtures
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class EquipmentTest : FunSpec({
    test("equip weapon") {
        val fighter = fighter()
        val dagger = ItemFixtures.dagger
        fighter.equip(dagger)

        fighter.weapon() shouldBe dagger
    }
})
