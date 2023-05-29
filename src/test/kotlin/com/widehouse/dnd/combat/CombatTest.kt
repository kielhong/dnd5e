package com.widehouse.dnd.combat

import com.widehouse.dnd.character.ability.Dexterity
import com.widehouse.dnd.character.nonplayer.Monster
import com.widehouse.dnd.character.player.PlayerCharacter
import com.widehouse.dnd.dice.Dice
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainInOrder
import io.mockk.every
import io.mockk.mockk

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

    }

//    test("every round, every character take turn") {
//        val char = spyk(fighter())
//        val monster = spyk(goblin)
//        val combat = Combat(playerCharacters = listOf(char), monsters = listOf(monster))
//        combat.initiative()
//        // when
//        combat.round()
//        // then
//        verify { char.attack(monster) }
//        verify { monster.attack(char) }
//    }
//
//    test("characters or monsters are all dead then combat end") {
//        val pc = spyk(fighter())
//        val monster = spyk(goblin)
//        every { monster.dead() }.returns(true)
//        val combat = Combat(playerCharacters = listOf(pc), monsters = listOf(monster))
//        combat.initiative()
//        // when
//        val result = combat.combat()
//        // then
//        result.report() shouldBe listOf(pc)
//    }
//
//    test("roundResult has any empty list then combat end") {
//        val char = fighter()
//        val monster = goblin
//
//        var result = RoundResult(listOf(char), listOf(char), listOf(monster))
//        result.endCombat() shouldBe true
//        result = RoundResult(listOf(monster), listOf(char), listOf(monster))
//        result.endCombat() shouldBe true
//    }
//
//    test("If a character is dead, then remove from roundOrder") {
//        val pc = spyk(fighter())
//        val monster = spyk(goblin)
//        every { monster.dead() } returns true
//        val combat = Combat(listOf(pc), listOf(monster))
//        combat.initiative()
//        // when
//        val turnResult = combat.turn(pc)
//        // then
//        turnResult.resolve() shouldNotContain monster
//    }
}
