package com.widehouse.dnd5e.combat;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import com.widehouse.dnd5e.character.Ability;
import com.widehouse.dnd5e.character.Character;
import com.widehouse.dnd5e.dice.Dice;
import com.widehouse.dnd5e.equipment.Weapon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CombatTest {
    @Mock
    private Dice dice;

    @Test
    void attackRoll_GreaterThanOrEqualArmorClass_ThenHitAndDamageResult() {
        // given
        Character character = Character.builder()
                .ability(new Ability(15, 14, 13, 12, 10, 8))
                .create();
        given(dice.rollSum())
                .willReturn(12)   // attack roll
                .willReturn(5);   // weapon damage
        // when
        Weapon weapon = new Weapon("Longsword", 1500, 3, dice, Weapon.WeaponCategory.MARTIAL);
        Combat combat = new Combat();
        CombatResult result = combat.attack(dice, character, weapon, 15);
        // then
        then(result)
                .hasFieldOrPropertyWithValue("hit", true)
                .hasFieldOrPropertyWithValue("damage", 5);
    }

    @Test
    void attackRoll_UnderArmorClass_ThenMiss() {
        // given
        Character character = Character.builder()
                .ability(new Ability(15, 14, 13, 12, 10, 8))
                .create();
        given(dice.rollSum())
                .willReturn(10)   // attack roll
                .willReturn(5);   // weapon damage
        // when
        Weapon weapon = new Weapon("Longsword", 1500, 3, dice, Weapon.WeaponCategory.MARTIAL);
        Combat combat = new Combat();
        CombatResult result = combat.attack(dice, character, weapon, 15);
        // then
        then(result)
                .hasFieldOrPropertyWithValue("hit", false)
                .hasFieldOrPropertyWithValue("damage", 0);
    }

    @Test
    void attackRoll_Given20_ThenHitWithCritical() {
        // given
        Character character = Character.builder()
                .ability(new Ability(15, 14, 13, 12, 10, 8))
                .create();
        given(dice.rollSum())
                .willReturn(20)   // attack roll
                .willReturn(3)    // weapon damage 1
                .willReturn(4);   // weapon damage 2
        // when
        Weapon weapon = new Weapon("Longsword", 1500, 3, dice, Weapon.WeaponCategory.MARTIAL);
        Combat combat = new Combat();
        CombatResult result = combat.attack(dice, character, weapon, 30);
        // then
        then(result)
                .hasFieldOrPropertyWithValue("hit", true)
                .hasFieldOrPropertyWithValue("damage", 7);
    }
}