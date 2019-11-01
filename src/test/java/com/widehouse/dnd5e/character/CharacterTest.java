package com.widehouse.dnd5e.character;

import static com.widehouse.dnd5e.character.Alignment.LAWFUL_EVIL;
import static com.widehouse.dnd5e.character.Alignment.LAWFUL_GOOD;
import static com.widehouse.dnd5e.character.Alignment.NEUTRAL;
import static com.widehouse.dnd5e.character.Alignment.NEUTRAL_EVIL;
import static com.widehouse.dnd5e.character.Alignment.NEUTRAL_GOOD;
import static com.widehouse.dnd5e.character.CharacterClass.BARD;
import static com.widehouse.dnd5e.character.CharacterClass.CLERIC;
import static com.widehouse.dnd5e.character.CharacterClass.DRUID;
import static com.widehouse.dnd5e.character.CharacterClass.FIGHTER;
import static com.widehouse.dnd5e.character.CharacterClass.RANGER;
import static com.widehouse.dnd5e.character.Race.DWARF;
import static com.widehouse.dnd5e.character.Race.ELF;
import static com.widehouse.dnd5e.character.Race.HALFLING;
import static com.widehouse.dnd5e.character.Race.HALF_ORC;
import static com.widehouse.dnd5e.character.Race.HUMAN;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class CharacterTest {
    @Test
    void constructor() {
        Character character = Character.builder()
                .characterName("Foo Bar")
                .characterClass(CLERIC)
                .race(HUMAN)
                .alignment(LAWFUL_GOOD)
                .create();

        then(character)
                .hasFieldOrPropertyWithValue("characterName", "Foo Bar")
                .hasFieldOrPropertyWithValue("characterClass", CLERIC)
                .hasFieldOrPropertyWithValue("race", HUMAN)
                .hasFieldOrPropertyWithValue("alignment", Alignment.NEUTRAL_GOOD)
                .hasFieldOrPropertyWithValue("xp", 0)
                .hasFieldOrPropertyWithValue("level", 1)
                .hasFieldOrProperty("hp");
    }

    @Test
    @DisplayName("Ability 지정시 각 능력치 지정")
    void setAbility_ThenSetAbilities() {
        // given
        Character character = Character.builder()
                .characterName("Foo Bar")
                .characterClass(BARD)
                .race(ELF)
                .alignment(Alignment.LAWFUL_NEUTRAL)
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

    @Test
    @DisplayName("경험치(XP)를 얻으면 해당 XP만큼 캐릭터 XP 증가")
    void earnXp_ThenIncreaseXp() {
        // given
        Character character = Character.builder()
                .characterName("Foo Bar")
                .characterClass(FIGHTER)
                .race(DWARF)
                .alignment(LAWFUL_EVIL)
                .create();
        // when
        character.earnXp(100);
        // then
        then(character.getXp())
                .isEqualTo(100);
    }

    @DisplayName("XP가 특정값을 넘으면 Level 상승")
    @ParameterizedTest
    @CsvSource({"300,2", "6500,5", "85000,11", "305000,19"})
    void earnXp_thenAdvanceLevel(Integer xp, Integer level) {
        // given
        Character character = Character.builder()
                .characterName("Foo Bar")
                .characterClass(DRUID)
                .race(HALFLING)
                .alignment(NEUTRAL)
                .create();
        // when
        character.earnXp(xp);
        // then
        then(character.getLevel()).isEqualTo(level);
    }

    @ParameterizedTest
    @EnumSource(CharacterClass.class)
    void createCharacter_ThenLevel1Hp(CharacterClass characterClass) {
        // given
        Character character = Character.builder()
                .characterName("Foo Bar")
                .characterClass(characterClass)
                .race(HALF_ORC)
                .alignment(NEUTRAL_GOOD)
                .create();
        character.setAbilities(15, 14, 13, 12, 10, 8);
        // when
        Integer hp = character.getMaxHitPoints();
        // then
        then(hp).isEqualTo(character.getCharacterClass().getHitDie().getSide() + 1);
    }

    @Test
    void levelUp_ThenIncreaseMaxHp() {
        // level 1, con modifier 1, ranger d10
        // given
        Character character = Character.builder()
                .characterName("Foo Bar")
                .characterClass(RANGER)
                .race(HALF_ORC)
                .alignment(NEUTRAL_EVIL)
                .create();
        character.setAbilities(15, 14, 13, 12, 10, 8);
        // when level up to 2
        character.earnXp(300);
        // then
        then(character.getMaxHitPoints())
                .isBetween(10 + 1 + 1 + 1, 10 + 1 + 10 + 1);
    }
}
