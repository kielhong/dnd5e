package com.widehouse.dnd.character

import com.widehouse.dnd.character.MonsterFixtures.Companion.goblin
import com.widehouse.dnd.character.Size.Small
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MonsterTest : FunSpec({
    test("monster construct") {
        val monster = Monster(name = "goblin", size = Small, type = "Humanoid", hitPoints = 7, abilities = Abilities(8, 14, 10, 10, 8, 8), armorClass = 15)

        monster.armorClass shouldBe 15
        monster.hitPoints shouldBe 7
        monster.strength.score shouldBe 8
        monster.size shouldBe Small
        monster.type shouldBe "Humanoid"
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

    test("monster get damage then reduce hitPoint") {
        val hitPoint = goblin.hitPoints
        val monster = goblin
        monster.getDamage(5)

        monster.hitPoints shouldBe hitPoint - 5
    }
})
