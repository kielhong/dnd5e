package com.widehouse.dnd5e.equipment;

import com.widehouse.dnd5e.dice.Dice;
import lombok.Getter;

@Getter
public class Weapon extends Item {
    public enum WeaponCategory {
        SIMPLE, MARTIAL
    }

    private Dice damage;
    private WeaponCategory category;

    public Weapon(String name, Integer cost, Integer weight, Dice damage, WeaponCategory category) {
        super(name, cost, weight);
        this.damage = damage;
        this.category = category;
    }
}
