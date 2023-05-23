package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.CharacterFixtures.fighter
import com.widehouse.dnd.character.MonsterFixtures.Companion.goblin
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify

class CombatTest : FunSpec({
    test("initiative step every participant roll then place in the Initiative order") {
        // participant
        val char1 = fighter()
        val char2 = fighter()
        val monster1 = goblin
        val monster2 = goblin
        // combat
        val combat = Combat(characterOlds = listOf(char1, char2), monsters = listOf(monster1, monster2))
        // when
        combat.initiative()
        // then
        combat.roundOrder() shouldContainAll listOf(char1, char2, monster1, monster2)
    }

    test("every round, every character take turn") {
        val char = spyk(fighter())
        val monster = spyk(goblin)
        val combat = Combat(characterOlds = listOf(char), monsters = listOf(monster))
        combat.initiative()
        // when
        combat.round()
        // then
        verify { char.attack(monster) }
        verify { monster.attack(char) }
    }

    test("characters or monsters are all dead then combat end") {
        val pc = spyk(fighter())
        val monster = spyk(goblin)
        every { monster.dead() }.returns(true)
        val combat = Combat(characterOlds = listOf(pc), monsters = listOf(monster))
        combat.initiative()
        // when
        val result = combat.combat()
        // then
        result.report() shouldBe listOf(pc)
    }

    test("roundResult has any empty list then combat end") {
        val char = fighter()
        val monster = goblin

        var result = RoundResult(listOf(char), listOf(char), listOf(monster))
        result.endCombat() shouldBe true
        result = RoundResult(listOf(monster), listOf(char), listOf(monster))
        result.endCombat() shouldBe true
    }

    test("If a character is dead, then remove from roundOrder") {
        val pc = spyk(fighter())
        val monster = spyk(goblin)
        every { monster.dead() } returns true
        val combat = Combat(listOf(pc), listOf(monster))
        combat.initiative()
        // when
        val turnResult = combat.turn(pc)
        // then
        turnResult.resolve() shouldNotContain monster
    }
})
