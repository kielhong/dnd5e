package com.widehouse.dnd5e.monster;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Monster {
    private String name;
    private Integer armorClass;
    private Integer hitPoint;
    private Integer xp;

    public void earnDamage(Integer damage) {
        this.hitPoint -= damage;
    }
}
