package com.widehouse.dnd5e.character;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class AbilityTest {

    @Test
    void constructor_GivenStatScore_ThenCreateAbility() {
        Ability ability = new Ability(Stat.of(30), Stat.of(15), Stat.of(12), Stat.of(10), Stat.of(6), Stat.of(5));
        // then
        then(ability)
                .hasFieldOrPropertyWithValue("strength.score", 30)
                .hasFieldOrPropertyWithValue("dexterity.score", 15)
                .hasFieldOrPropertyWithValue("constitution.score", 12)
                .hasFieldOrPropertyWithValue("intelligence.score", 10)
                .hasFieldOrPropertyWithValue("wisdom.score", 6)
                .hasFieldOrPropertyWithValue("charisma.score", 5);
    }

    @Test
    void modifier_GivenStatScore_ThenReturnModifierValue() {
        Ability ability = new Ability(Stat.of(30), Stat.of(15), Stat.of(12), Stat.of(10), Stat.of(6), Stat.of(5));
        // then
        then(ability)
                .hasFieldOrPropertyWithValue("strength.modifier", 10)
                .hasFieldOrPropertyWithValue("dexterity.modifier", 2)
                .hasFieldOrPropertyWithValue("constitution.modifier", 1)
                .hasFieldOrPropertyWithValue("intelligence.modifier", 0)
                .hasFieldOrPropertyWithValue("wisdom.modifier", -2)
                .hasFieldOrPropertyWithValue("charisma.modifier", -3);
    }
}