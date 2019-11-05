package com.widehouse.dnd5e.combat;

import static com.widehouse.dnd5e.character.Alignment.NEUTRAL;
import static com.widehouse.dnd5e.character.CharacterClass.FIGHTER;
import static com.widehouse.dnd5e.character.Race.HUMAN;
import static com.widehouse.dnd5e.dice.DieType.D8;
import static org.assertj.core.api.BDDAssertions.then;

import com.widehouse.dnd5e.character.Ability;
import com.widehouse.dnd5e.character.Character;
import com.widehouse.dnd5e.dice.Dice;
import com.widehouse.dnd5e.equipment.Weapon;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class CombatTest {
    @Mock
    private Dice dice;

    @Test
    void attack_ThenHitAndDamageResult() {
        // given
        Character character = Character.builder()
                .characterName("Foo Bar")
                .characterClass(FIGHTER)
                .race(HUMAN)
                .alignment(NEUTRAL)
                .ability(new Ability(15, 14, 13, 12, 10, 8))
                .create();
//        given(dice.rollSum())
//                .willReturn(12);
        // when
        Weapon weapon = new Weapon("Longsword", 1500, 3, Dice.of(1, D8), Weapon.WeaponCategory.MARTIAL);
        Combat combat = new Combat();
        CombatResult result = combat.attack(character, weapon, 15);
        // then
        System.out.println(result);
        then(result)
                .hasFieldOrPropertyWithValue("hit", true)
                .hasFieldOrPropertyWithValue("damage", 5);


    }
}