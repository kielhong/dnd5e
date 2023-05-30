package com.widehouse.dnd.combat

import com.widehouse.dnd.character.ability.Dexterity
import com.widehouse.dnd.character.ability.Strength
import com.widehouse.dnd.character.nonplayer.Monster
import com.widehouse.dnd.character.player.PlayerCharacter
import com.widehouse.dnd.dice.Dice
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainInOrder
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify

class CombatTest : FreeSpec() {
    init {
        beforeEach {
            clearAllMocks()
        }

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

        // 시나리오 만들기 너무 복잡해서 제거
//        "combat until pcs or monsters all die" {
//            val char1 = mockk<PlayerCharacter>()
//            every { char1.abilities.dexterity } returns Dexterity(10)
//            every { char1.attacks.modifier() } returns 1
//            every { char1.armorClass } returns 10
//            every { char1.proficiencyBonus } returns 2
//            every { char1.hitPoints } returns 20
//            justRun { char1 setProperty "hitPoints" value any<Int>() }
//            every { char1.damageRoll() } returns 10
//            every { char1.dead() } returns false
//            val char2 = mockk<PlayerCharacter>()
//            every { char2.abilities.dexterity } returns Dexterity(10)
//            every { char2.attacks.modifier() } returns 1
//            every { char2.proficiencyBonus } returns 2
//            every { char2.damageRoll() } returns 10
//            every { char2.dead() } returns false andThen true
//            val monster1 = mockk<Monster>()
//            every { monster1.abilities.dexterity } returns Dexterity(10)
//            every { monster1.armorClass } returns 2
//            every { monster1.damageRoll() } returns 2
//            every { monster1.hitPoints } returns 10
//            justRun { monster1 setProperty "hitPoints" value any<Int>() }
//            every { monster1.dead() } returns false andThen true
//            val monster2 = mockk<Monster>()
//            every { monster2.abilities.dexterity } returns Dexterity(10)
//            every { monster2.damageRoll() } returns 2
//            every { monster2.dead() } returns true
//
//            val combat = spyk(Combat(playerCharacters = listOf(char1, char2), monsters = listOf(monster1, monster2)))
//            combat.initiativeRoll(Dice.D20)
//            // when
//            combat.combat()
//            // then
//            verify(exactly = 2) { combat.round() }
//        }

        "Combat Action" {
            val dice: Dice = mockk()
            every { dice.roll() } returnsMany listOf(10, 10)
            val char1 = mockk<PlayerCharacter> {
                every { abilities.dexterity } returns Dexterity(12)
                every { abilities.strength } returns Strength(12)
                every { armorClass } returns 30
                every { attacks.modifier() } returns 1
                every { proficiencyBonus } returns 1
                every { damageRoll() } returns 8
                every { dead() } returns false
            }
            val monster1 = mockk<Monster> {
                every { abilities.dexterity } returns Dexterity(10)
                every { armorClass } returns 5
                every { damageRoll() } returns 4
                every { hitPoints } returns 10
                every { dead() } returns false andThen true
            }
            justRun { monster1 setProperty "hitPoints" value any<Int>() }

            val combat = spyk(Combat(playerCharacters = listOf(char1), monsters = listOf(monster1)))
            combat.initiativeRoll(dice)
            // when
            combat.combat()
            // then
            verify {
                combat.actionAttack(char1)
                combat.attackRoll(monster1, listOf(1, 1), Dice.D20)
                combat.actionAttack(monster1)
                combat.attackRoll(char1, listOf(), Dice.D20)
            }
        }
    }
}
