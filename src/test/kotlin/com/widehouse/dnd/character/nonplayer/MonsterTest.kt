package com.widehouse.dnd.character.nonplayer

import com.widehouse.dnd.character.Abilities
import com.widehouse.dnd.character.nonplayer.MonsterSize.Small
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MonsterTest : FunSpec({
    test("monster construct") {
        val monster = Monster("goblin", Abilities.of(8, 8, 8, 8, 8, 8), 7, Small, "Humanoid", armorClass = 15)

        monster.abilities.strength.score shouldBe 8
        monster.hitPoints shouldBe 7
        monster.size shouldBe Small
        monster.type shouldBe "Humanoid"
        monster.armorClass shouldBe 15
    }

//    test("when monster attack, attack roll + hit bonus") {
//        val dice = mockk<Dice>()
//        every { Dice.D20.roll() }.returns(15)
//        val characterOld = mockk<CharacterOld>()
//        every { characterOld.armorClass }.returns(15)
//        val action = mockk<Action>()
//        every { action.hitBonus }.returns(5)
//        val monster = spyk(Monster("goblin", Small, "Humanoid", Abilities(8, 14, 10, 10, 8, 8), 7, 15, action), recordPrivateCalls = true)
//        val f = Monster::class.java.getDeclaredField("dice")
//        f.isAccessible = true
//        f.set(monster, dice)
//        monster.attackRoll(characterOld)
//
//        verify { action.hitBonus }
//        verify { characterOld.armorClass }
//    }

//    test("monster get damage then reduce hitPoint") {
//        val hitPoint = goblin.hitPoints
//        val monster = goblin
//        monster.getDamage(5)
//
//        monster.hitPoints shouldBe hitPoint - 5
//    }
})
