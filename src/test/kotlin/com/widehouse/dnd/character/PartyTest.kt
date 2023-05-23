package com.widehouse.dnd.character

import com.widehouse.dnd.character.CharacterFixtures.Companion.fighter
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.mockk.mockk

class PartyTest : BehaviorSpec({
    given("a party") {
        val party = Party()
        `when`("a member join to it") {
            val member = mockk<CharacterOld>()
            party.join(member)

            then("member should be member of party") {
                party.members shouldContain member
            }
        }
    }

    given("a party of member 4") {
        val party = Party()
        for (i in 0..2) {
            party.join(mockk())
        }
        val fighter = fighter()
        party.join(fighter)
        `when`("a member join to it") {
            val member = mockk<CharacterOld>()
            party.join(member)

            then("member should not be member of party") {
                party.members.size shouldBe 4
                party.members shouldNotContain member
            }
        }
        `when`("a member leave the party") {
            party.leave(fighter)
            then("the member should not be member of party") {
                party.members.size shouldBe 3
                party.members shouldNotContain fighter
            }
        }
    }
})
