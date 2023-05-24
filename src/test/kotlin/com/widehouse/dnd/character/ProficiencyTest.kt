package com.widehouse.dnd.character

import com.widehouse.dnd.character.Class.Wizard
import com.widehouse.dnd.character.Race.Elf
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ProficiencyTest : FunSpec({
    test("proficiency bonus by level") {
        val list = mapOf(1 to 2, 4 to 2, 5 to 3, 8 to 3, 10 to 4, 15 to 5, 17 to 6, 20 to 6)
        for ((level, proficiency) in list) {
            val char = PlayerCharacter("foo", Wizard, level, Elf, maxHitPoints = 20)
            char.proficiencyBonus shouldBe proficiency
        }
    }
})
