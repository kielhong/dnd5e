package com.widehouse.dnd.character

import com.widehouse.dnd.character.ability.Dexterity
import com.widehouse.dnd.character.ability.Strength
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AbilityTest : FunSpec({
    test("Ability modifier by score") {
        val str = Strength(15)
        str.score shouldBe 15
        str.modifier shouldBe 2

        val dex = Dexterity(13)
        dex.score shouldBe 13
        dex.modifier shouldBe 1
    }
})
