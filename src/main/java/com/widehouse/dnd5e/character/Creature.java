package com.widehouse.dnd5e.character;

public interface Creature {
    String getName();
    Ability getAbility();
    Integer getInitiative();

    void setInitiative(Integer initiative);
}
