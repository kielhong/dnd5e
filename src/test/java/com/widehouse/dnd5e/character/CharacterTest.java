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
                .hasFieldOrPropertyWithValue("alignment", Alignment.NEUTRAL_GOOD)
                .hasFieldOrPropertyWithValue("xp", 0)
                .hasFieldOrPropertyWithValue("level", 1);
    }

    @Test
    void setAbility_ThenSetAbilities() {
        // given
        Character character = Character.builder()
                .characterName("Foo Bar")
                .characterClass(CharacterClass.BARD)
                .race(Race.ELF)
                .alignment(Alignment.LAWFUL_EVIL)
                .create();
        // when
        character.setAbilities(15, 14, 13, 12, 10, 8);
        // then
        then(character)
                .hasFieldOrPropertyWithValue("ability.strength.score", 15)
                .hasFieldOrPropertyWithValue("ability.dexterity.score", 14)
                .hasFieldOrPropertyWithValue("ability.constitution.score", 13)
                .hasFieldOrPropertyWithValue("ability.intelligence.score", 12)
                .hasFieldOrPropertyWithValue("ability.wisdom.score", 10)
                .hasFieldOrPropertyWithValue("ability.charisma.score", 8);
    }
}
