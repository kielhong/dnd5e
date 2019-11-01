package com.widehouse.dnd5e.character;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Ability {
    Stat strength;
    Stat dexterity;
    Stat constitution;
    Stat intelligence;
    Stat wisdom;
    Stat charisma;

    /**
     * constructor.
     */
    public Ability(int str, int dex, int con, int intel, int wis, int cha) {
        this.strength = Stat.of(str);
        this.dexterity = Stat.of(dex);
        this.constitution = Stat.of(con);
        this.intelligence = Stat.of(intel);
        this.wisdom = Stat.of(wis);
        this.charisma = Stat.of(cha);
    }
}
