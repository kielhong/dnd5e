package com.widehouse.dnd.character.player

import com.widehouse.dnd.character.PlayerCharacterFixtures
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks

class PlayerCharacterXpTest : FreeSpec({
    lateinit var character: PlayerCharacter

    beforeEach {
        character = PlayerCharacterFixtures.wizard.apply {
            level = 1
            experiencePoints = 0
        }
    }

    beforeEach {
        clearAllMocks()
    }

    "Gain XP" {
        character.gainXp(100)

        character.experiencePoints shouldBe 100
    }

    "Level Up" - {
        "check enough xp for level up" {
            character.gainXp(400)
            val result = character.checkLevelUp()

            result shouldBe true
        }

        "enough xp then advance" {
            character.apply {
                maxHitPoints = 8
            }

            character.gainXp(400)
            character.levelUp()

            character.level shouldBe 2
            character.experiencePoints shouldBe 400
            character.maxHitPoints shouldBe 8 + character.`class`.hitDice.side + character.abilities.constitution.modifier
        }

        "not enough xp then do nothing" {
            character.gainXp(200)
            character.levelUp()

            character.level shouldBe 1
            character.experiencePoints shouldBe 200
        }
    }
})
