package com.widehouse.dnd.character.player

import com.widehouse.dnd.character.Abilities
import com.widehouse.dnd.character.AbilityType
import com.widehouse.dnd.character.Constitution
import com.widehouse.dnd.character.Strength
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class PlayerCharacterTest : FreeSpec({
    "Player Character creation test" {
        val char = PlayerCharacter.create(name = "foo", race = Race.Human, `class` = Class.Fighter, abilities = Abilities.of(
            15,
            14,
            13,
            12,
            10,
            8
        )
        )

        assertSoftly(char) {
            name shouldBe "foo"
            race shouldBe Race.Human
            `class` shouldBe Class.Fighter
            level shouldBe 1
            abilities.strength shouldBe Strength(15)
            abilities.constitution shouldBe Constitution(13)
            hitPoints shouldBe 11
            proficiencyBonus shouldBe 2
            proficiencySavingThrow shouldContain AbilityType.Strength
            proficiencySavingThrow shouldContain AbilityType.Constitution
        }
    }
})
