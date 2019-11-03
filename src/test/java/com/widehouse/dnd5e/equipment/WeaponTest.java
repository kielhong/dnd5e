package com.widehouse.dnd5e.equipment;

import static com.widehouse.dnd5e.dice.DieType.D8;
import static com.widehouse.dnd5e.equipment.Weapon.WeaponCategory.MARTIAL;
import static org.assertj.core.api.BDDAssertions.then;

import com.widehouse.dnd5e.dice.Dice;
import org.junit.jupiter.api.Test;

class WeaponTest {
    @Test
    void weapon_constructor() {
        // when
        Dice damage = Dice.of(1, D8);
        Weapon weapon = new Weapon("BattleAxe", 100, 4, damage, MARTIAL);
        // then
        then(weapon)
                .hasFieldOrPropertyWithValue("name", "BattleAxe")
                .hasFieldOrPropertyWithValue("cost", 100)
                .hasFieldOrPropertyWithValue("weight", 4)
                .hasFieldOrPropertyWithValue("damage", damage)
                .hasFieldOrPropertyWithValue("category", MARTIAL);
    }
}