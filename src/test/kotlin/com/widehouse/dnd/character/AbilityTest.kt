package com.widehouse.dnd.character

import com.widehouse.dnd.Ability
import com.widehouse.dnd.Charisma
import com.widehouse.dnd.Constitution
import com.widehouse.dnd.Dexterity
import com.widehouse.dnd.Intelligence
import com.widehouse.dnd.Strength
import com.widehouse.dnd.Wisdom
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AbilityTest : FunSpec({
    test("Ability modifier by score") {
        val ability = mapOf("str" to Strength(15), "dex" to Dexterity(13))
        val str = ability["str"]!!
        str.score() shouldBe 15
        str.modifier() shouldBe 2

        val dex = ability["dex"]!!
        dex.score() shouldBe 13
        dex.modifier() shouldBe 1
    }

    test("abilities test") {
        val ability = Ability.builder(str = 15, dex = 13, con = 13, int = 12, wis = 11, cha = 10)
        ability["str"] shouldBe Strength(15)
        ability["dex"] shouldBe Dexterity(13)
        ability["con"] shouldBe Constitution(13)
        ability["int"] shouldBe Intelligence(12)
        ability["wis"] shouldBe Wisdom(11)
        ability["cha"] shouldBe Charisma(10)
    }
})
