package com.widehouse.dnd5e.monster;

import lombok.Builder;

@Builder
public class Monster {
    private String name;
    private Integer armorClass;
    private Integer hitPoint;
    private Integer xp;
}
