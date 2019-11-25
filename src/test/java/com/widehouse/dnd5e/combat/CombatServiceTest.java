package com.widehouse.dnd5e.combat;

import static com.widehouse.dnd5e.character.Alignment.CHAOTIC_EVIL;
import static com.widehouse.dnd5e.character.Alignment.CHAOTIC_GOOD;
import static com.widehouse.dnd5e.character.CharacterClass.ROGUE;
import static com.widehouse.dnd5e.character.CharacterClass.SORCERER;
import static com.widehouse.dnd5e.character.Race.HALF_ELF;
import static com.widehouse.dnd5e.character.Race.HALF_ORC;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import com.widehouse.dnd5e.character.Ability;
import com.widehouse.dnd5e.character.Character;
import com.widehouse.dnd5e.dice.Dice;
import com.widehouse.dnd5e.monster.Monster;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CombatServiceTest {
    private CombatService service;

    @Mock
    private Dice dice;
    private List<Character> characters = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();

    @BeforeEach
    void setUp() {
        characters.add(Character.builder()
                .characterName("PC1")
                .characterClass(ROGUE)
                .race(HALF_ELF)
                .alignment(CHAOTIC_GOOD)
                .ability(new Ability(15, 14, 13, 12, 10, 8))
                .build());
        characters.add(Character.builder()
                .characterName("PC2")
                .characterClass(SORCERER)
                .race(HALF_ORC)
                .alignment(CHAOTIC_EVIL)
                .ability(new Ability(15, 14, 13, 12, 10, 8))
                .build());

        monsters.add(Monster.builder()
                .name("Goblin")
                .armorClass(15)
                .hitPoint(7)
                .xp(50)
                .ability(new Ability(15, 14, 13, 12, 10, 8))
                .build());

        service = new CombatService(dice, characters, monsters);
    }

    @DisplayName("Combat 시 character 와 monster initiative 결정")
    @Test
    void rollInitiative_ThenRollInitiativeAll() {
        given(dice.rollSum())
                .willReturn(18, 3, 5);
        // when
        service.rollInitiative();
        // then
        then(service.getCreatures())
                .extracting("name", "initiative")
                .containsOnly(tuple("PC1", 20), tuple("Goblin", 7), tuple("PC2",5));
    }
}