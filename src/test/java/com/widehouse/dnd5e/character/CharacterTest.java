package com.widehouse.dnd5e.character;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class CharacterTest {
    @Test
    void constructor() {
        Character character = Character.builder()
                .characterName("Foo Bar")
                .characterClass(CharacterClass.CLERIC)
                .race(Race.HUMAN)
                .alignment(Alignment.NEUTRAL_GOOD)
                .create();

        then(character)
                .hasFieldOrPropertyWithValue("characterName", "Foo Bar")
                .hasFieldOrPropertyWithValue("characterClass", CharacterClass.CLERIC)
                .hasFieldOrPropertyWithValue("race", Race.HUMAN)
                .hasFieldOrPropertyWithValue("alignment", Alignment.NEUTRAL_GOOD);
    }
}
