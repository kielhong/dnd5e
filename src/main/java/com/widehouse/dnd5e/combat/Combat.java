package com.widehouse.dnd5e.combat;

import com.widehouse.dnd5e.character.Character;
import com.widehouse.dnd5e.dice.Dice;
import com.widehouse.dnd5e.equipment.Weapon;
import com.widehouse.dnd5e.monster.Monster;

public class Combat {
    public CombatResult attack(Dice dice, Character character, Weapon weapon, Monster monster) {
        Integer diceResult = dice.rollSum();
        Boolean critical = isCritical(diceResult);
        Boolean hit = critical || diceResult + getAttackBonus(character) >= monster.getArmorClass();
        Integer damage = hit ? getDamage(weapon, critical) : 0;

        return new CombatResult(hit, damage);
    }

    private boolean isCritical(Integer diceResult) {
        return diceResult == 20;
    }

    private Integer getAttackBonus(Character character) {
        return character.getAbility().getStrength().getModifier() + character.getProficiency();
    }

    private Integer getDamage(Weapon weapon, Boolean critical) {
        return critical
                ? weapon.getDamage().rollSum() + weapon.getDamage().rollSum()
                : weapon.getDamage().rollSum();
    }
}
