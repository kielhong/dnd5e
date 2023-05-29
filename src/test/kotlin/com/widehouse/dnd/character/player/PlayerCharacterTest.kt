package com.widehouse.dnd.character.player

import com.widehouse.dnd.character.PlayerCharacterFixtures
import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.character.ability.AbilityType
import com.widehouse.dnd.item.ItemFixtures
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk

class PlayerCharacterTest : FreeSpec({
    beforeEach {
        clearAllMocks()
    }

    "Player Character creation test" {
        val character = PlayerCharacter.create("foo", Race.Human, Class.Fighter, Abilities.of(15, 14, 13, 12, 10, 8))

        assertSoftly(character) {
            name shouldBe "foo"
            race shouldBe Race.Human
            `class` shouldBe Class.Fighter
            level shouldBe 1
            abilities.strength.score shouldBe 15
            abilities.constitution.score shouldBe 13
            hitPoints shouldBe 11
            proficiencyBonus shouldBe 2
            proficiencySavingThrow shouldContain AbilityType.Strength
            proficiencySavingThrow shouldContain AbilityType.Constitution
        }
    }

    "Player Character ability init" {
        val character = PlayerCharacter.create("foo", Race.Human, Class.Fighter, Abilities.of(12, 13, 14, 15, 16, 17))

        assertSoftly(character) {
            abilities.strength.score shouldBe 12
            abilities.dexterity.score shouldBe 13
            abilities.constitution.score shouldBe 14
            abilities.intelligence.score shouldBe 15
            abilities.wisdom.score shouldBe 16
            abilities.charisma.score shouldBe 17
        }
    }

    "Player switch weapon" {
        val character = PlayerCharacterFixtures.rogue
        val weapon = ItemFixtures.dagger
        character.switchWeapon(weapon)
        // then
        character.attacks.weapons shouldBe mutableListOf(weapon)
    }

    "Player is dead" {
        val character = spyk(PlayerCharacterFixtures.wizard)
        every { character.hitPoints } returns 0

        character.dead() shouldBe true
    }
})
