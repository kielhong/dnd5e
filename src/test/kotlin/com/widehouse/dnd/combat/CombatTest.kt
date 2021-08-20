package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Ability
import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.CharacterClass.Fighter
import com.widehouse.dnd.character.CharacterClass.Monster
import com.widehouse.dnd.character.Dexterity
import com.widehouse.dnd.character.Race.Human
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
        val char1 = Character("foo1", Fighter, 1, Human, ability = mapOf("dex" to Dexterity(10)), maxHitPoints = 20)
        val char2 = Character("foo1", Fighter, 2, Human, ability = mapOf("dex" to Dexterity(10)), maxHitPoints = 20)
        val mon1 = Character("goblin1", Monster, 1, Human, ability = mapOf("dex" to Dexterity(10)), maxHitPoints = 20)
        val mon2 = Character("goblin2", Monster, 2, Human, ability = mapOf("dex" to Dexterity(10)), maxHitPoints = 20)
        // combat
        val combat = Combat(characters = listOf(char1, char2), monsters = listOf(mon1, mon2))
        // when
        combat.initiative()
        // then
        combat.roundOrder() shouldContainAll listOf(char1, char2, mon1, mon2)
    }

    test("every round, every character take turn") {
        val pc = spyk(Character("pc", Fighter, 1, Human, ability = Ability.builder(10, 10, 10, 10, 10, 10), maxHitPoints = 20))
        val monster = spyk(Character("monster", Monster, 2, Human, ability = Ability.builder(10, 10, 10, 10, 10, 10), maxHitPoints = 20))
        val combat = Combat(characters = listOf(pc), monsters = listOf(monster))
        combat.initiative()
        // when
        combat.round()
        // then
        verify { pc.attack(monster) }
        verify { monster.attack(pc) }
    }

    test("characters or monsters are all dead then combat end") {
        val pc = spyk(Character("pc", Fighter, 1, Human, ability = Ability.builder(10, 10, 10, 10, 10, 10), maxHitPoints = 20))
        val monster = spyk(Character("monster", Monster, 2, Human, ability = Ability.builder(10, 10, 10, 10, 10, 10), maxHitPoints = 20))
        every { monster.dead() }.returns(true)
        val combat = Combat(characters = listOf(pc), monsters = listOf(monster))
        combat.initiative()
        // when
        val result = combat.combat()
        // then
        result.report() shouldBe listOf(pc)
    }

    test("roundResult has any empty list then combat end") {
        val pc = Character("pc", Fighter, 1, Human, ability = Ability.builder(10, 10, 10, 10, 10, 10), maxHitPoints = 20)
        val monster = Character("monster", Monster, 2, Human, ability = Ability.builder(10, 10, 10, 10, 10, 10), maxHitPoints = 20)

        var result = RoundResult(listOf(pc), listOf(pc), listOf(monster))
        result.endCombat() shouldBe true
        result = RoundResult(listOf(monster), listOf(pc), listOf(monster))
        result.endCombat() shouldBe true
    }

    test("If a character is dead, then remove from roundOrder") {
        val pc = spyk(Character("pc", Fighter, 1, Human, ability = Ability.builder(10, 10, 10, 10, 10, 10), maxHitPoints = 10))
        val monster = spyk(Character("monster", Monster, 2, Human, ability = Ability.builder(10, 10, 10, 10, 10, 10), maxHitPoints = 10))
        every { monster.dead() } returns true
        val combat = Combat(listOf(pc), listOf(monster))
        combat.initiative()
        // when
        val turnResult = combat.turn(pc)
        // then
        turnResult.resolve() shouldNotContain monster
    }
})
