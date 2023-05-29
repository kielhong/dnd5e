package com.widehouse.dnd.character.nonplayer

import com.widehouse.dnd.challenge.RollResult
import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.MonsterFixtures
import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.character.action.Action
import com.widehouse.dnd.character.item.Weapon
import com.widehouse.dnd.character.nonplayer.MonsterSize.Small
import com.widehouse.dnd.dice.Dice
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk

class MonsterTest : FunSpec({
    test("monster construct") {
        val monster = Monster("goblin", Abilities.of(8, 8, 8, 8, 8, 8), 7, Small, "Humanoid", armorClass = 15)

        monster.abilities.strength.score shouldBe 8
        monster.hitPoints shouldBe 7
        monster.size shouldBe Small
        monster.type shouldBe "Humanoid"
        monster.armorClass shouldBe 15
    }

    test("Attack Roll") {
        val monster = MonsterFixtures.goblin
        val dice = mockk<Dice>()
        val target = mockk<Character>()

        io.kotest.data.forAll(
            table(
                headers("roll", "modifiers", "armor class", "result"),
                row(1, listOf(1, 1), 10, RollResult.CriticalFail),
                row(8, listOf(1), 10, RollResult.Fail),
                row(8, listOf(1, 1), 10, RollResult.Success),
                row(10, listOf(0), 8, RollResult.Success),
                row(20, listOf(0), 30, RollResult.CriticalSuccess)
            )
        ) { roll: Int, modifiers: List<Int>, armorClass: Int, result: RollResult ->
            every { dice.roll() } returns roll
            every { target.armorClass } returns armorClass
            // then
            monster.attackRoll(target, modifiers, dice) shouldBe result
        }
    }

    test("Damage Roll") {
        val action = mockk<Action>()
        val weapon = mockk<Weapon>()
        every { action.weapon } returns weapon
        every { weapon.damageRoll() } returns 5
        val monster = Monster("goblin", Abilities.of(8, 8, 8, 8, 8, 8), 7, Small, "Humanoid", 15, action)

        monster.damageRoll() shouldBe 5
    }

    test("Monster is dead") {
        val monster = spyk(MonsterFixtures.goblin)
        every { monster.hitPoints } returns 0

        monster.dead() shouldBe true
    }
})
