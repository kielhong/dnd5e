package com.widehouse.dnd5e.combat;

import com.widehouse.dnd5e.character.Character;
import com.widehouse.dnd5e.dice.Dice;
import com.widehouse.dnd5e.dice.DieType;
import com.widehouse.dnd5e.equipment.Weapon;

public class Combat {
    public CombatResult attack(Character character, Weapon weapon, Integer armorClass) {
        Boolean hit = false;
        Integer attackRoll = Dice.of(1, DieType.D20).rollSum() + character.getAbility().getStrength().getModifier() + character.getProficiency();
        System.out.println("attackRoll = " + attackRoll + " vs ac =" + armorClass);
        if (attackRoll >= armorClass) {
            hit = true;
        }

        Integer damage = weapon.getDamage().rollSum();

        return new CombatResult(hit, damage);
    }
}
