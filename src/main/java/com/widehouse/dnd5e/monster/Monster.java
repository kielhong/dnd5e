package com.widehouse.dnd5e.monster;

import com.widehouse.dnd5e.character.Ability;
import com.widehouse.dnd5e.character.Creature;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Monster implements Creature {
    private String name;
    private Integer armorClass;
    private Integer hitPoint;
    private Integer xp;
    private Ability ability;
    private Integer initiative;

    public void earnDamage(Integer damage) {
        this.hitPoint -= damage;
    }

    @Override
    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    @Override
    public Integer getInitiative() {
        return this.initiative;
    }
}
