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
}
