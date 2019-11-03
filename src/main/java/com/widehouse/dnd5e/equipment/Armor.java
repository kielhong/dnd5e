package com.widehouse.dnd5e.equipment;

import lombok.Getter;

@Getter
public class Armor extends Item {
    public enum ArmorType {
        LIGHT_ARMOR, MEDIUM_ARMOR, HEAVY_ARMOR, SHIELD
    }

    private ArmorType armorType;

    public Armor(String name, Integer cost, Integer weight, ArmorType armorType) {
        super(name, cost, weight);
        this.armorType = armorType;
    }
}
