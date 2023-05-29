package com.widehouse.dnd.combat

import com.widehouse.dnd.character.MonsterFixtures.Companion.goblin
import com.widehouse.dnd.character.PlayerCharacterFixtures.fighter
import com.widehouse.dnd.character.ability.Dexterity
import com.widehouse.dnd.character.nonplayer.Monster
import com.widehouse.dnd.character.player.PlayerCharacter
import com.widehouse.dnd.dice.Dice
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainInOrder
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify

class CombatTest : FreeSpec() {
    init {
        "Determine initiative" - {
            val dice = mockk<Dice>()
            val char1 = mockk<PlayerCharacter>()
            val char2 = mockk<PlayerCharacter>()
            val monster1 = mockk<Monster>()
            val monster2 = mockk<Monster>()
            val combat = Combat(playerCharacters = listOf(char2, char1), monsters = listOf(monster2, monster1))

            "dice roll and same dex modifier" {
                every { dice.roll() } returns 13 andThen 15 andThen 10 andThen 11
                every { char1.abilities.dexterity } returns Dexterity(10)
                every { char2.abilities.dexterity } returns Dexterity(10)
                every { monster1.abilities.dexterity } returns Dexterity(10)
                every { monster2.abilities.dexterity } returns Dexterity(10)
                // when
                combat.initiativeRoll(dice)
                // then
                combat.initiativeOrder shouldContainInOrder listOf(char1, char2, monster1, monster2)
            }

            "same dice roll and dex modifier" {
                every { dice.roll() } returns 10 andThen 10 andThen 10 andThen 10
                every { char1.abilities.dexterity } returns Dexterity(15)
                every { char2.abilities.dexterity } returns Dexterity(13)
                every { monster1.abilities.dexterity } returns Dexterity(10)
                every { monster2.abilities.dexterity } returns Dexterity(8)
                // when
                combat.initiativeRoll(dice)
                // then
                combat.initiativeOrder shouldContainInOrder listOf(char1, char2, monster1, monster2)
            }
        }

        "combat until pcs or monsters all die" {
            val char1 = mockk<PlayerCharacter>()
            every { char1.abilities.dexterity } returns Dexterity(10)
            every { char1.dead() } returns false
            val char2 = mockk<PlayerCharacter>()
            every { char2.abilities.dexterity } returns Dexterity(10)
            every { char2.dead() } returns false andThen true
            val monster1 = mockk<Monster>()
            every { monster1.abilities.dexterity } returns Dexterity(10)
            every { monster1.dead() } returns false andThen true
            val monster2 = mockk<Monster>()
            every { monster2.abilities.dexterity } returns Dexterity(10)
            every { monster2.dead() } returns true

            val combat = spyk(Combat(playerCharacters = listOf(char1, char2), monsters = listOf(monster1, monster2)))
            combat.initiativeRoll(Dice.D20)
            // when
            combat.combat()
            // then
            verify(exactly = 2) { combat.round() }
        }

        "every round, every character take turn and action" {
            val char = fighter
            val monster = goblin
            val combat = spyk(Combat(playerCharacters = listOf(char), monsters = listOf(monster)))
            combat.initiativeRoll(Dice.D20)
            // when
            combat.round()
            // then
            verify { combat.action(char, monster) }
            verify { combat.action(monster, char) }
        }
    }
}
