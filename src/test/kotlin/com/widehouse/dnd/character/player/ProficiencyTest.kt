package com.widehouse.dnd.character.player

import com.widehouse.dnd.character.player.Class.Wizard
import com.widehouse.dnd.character.player.Race.Elf
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ProficiencyTest : FunSpec({
    test("proficiency bonus by level") {
        io.kotest.data.forAll(
            row(1, 2),
            row(4, 2),
            row(5, 3),
            row(8, 3),
            row(10, 4),
            row(15, 5),
            row(17, 6),
            row(20, 6)
        ) { level: Int, proficiency: Int ->
            val char = PlayerCharacter("foo", Elf, Wizard, level = level, maxHitPoints = 20)
            char.proficiencyBonus shouldBe proficiency
        }
    }
})
