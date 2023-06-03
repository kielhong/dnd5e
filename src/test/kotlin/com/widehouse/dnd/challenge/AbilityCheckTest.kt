package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.Skill
import com.widehouse.dnd.character.ability.AbilityType
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.RollSituation
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class AbilityCheckTest : FunSpec({
    val dice = mockk<Dice>()

    afterEach {
        clearAllMocks()
    }

    test("Ability checks") {
        every { dice.roll() }.returns(14)
        val rollSituation = RollSituation.of(dice)

        val check = AbilityCheck(rollSituation, listOf(2, 2), 15)

        check.result() shouldBe true
    }

    test("Skill related Ability") {
        io.kotest.data.forAll(
            row(AbilityType.Strength, Skill.Athletics),
            row(AbilityType.Dexterity, Skill.Acrobatics),
            row(AbilityType.Dexterity, Skill.SleightOfHand),
            row(AbilityType.Dexterity, Skill.Stealth),
            row(AbilityType.Intelligence, Skill.Arcana),
            row(AbilityType.Intelligence, Skill.History),
            row(AbilityType.Intelligence, Skill.Investigation),
            row(AbilityType.Intelligence, Skill.Nature),
            row(AbilityType.Intelligence, Skill.Religion),
            row(AbilityType.Wisdom, Skill.AnimalHandling),
            row(AbilityType.Wisdom, Skill.Insight),
            row(AbilityType.Wisdom, Skill.Medicine),
            row(AbilityType.Wisdom, Skill.Perception),
            row(AbilityType.Wisdom, Skill.Survival),
            row(AbilityType.Charisma, Skill.Deception),
            row(AbilityType.Charisma, Skill.Intimidation),
            row(AbilityType.Charisma, Skill.Performance),
            row(AbilityType.Charisma, Skill.Persuasion)
        ) { abilityType, skill ->
            abilityType shouldBe skill.abilityType
        }
    }
})
