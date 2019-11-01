package com.widehouse.dnd5e.character;

import static com.widehouse.dnd5e.dice.DieType.D10;
import static com.widehouse.dnd5e.dice.DieType.D12;
import static com.widehouse.dnd5e.dice.DieType.D6;
import static com.widehouse.dnd5e.dice.DieType.D8;

import com.widehouse.dnd5e.dice.DieType;
import lombok.Getter;

@Getter
public enum CharacterClass {
    BARBARIAN(D12), BARD(D8), CLERIC(D8), DRUID(D8), FIGHTER(D10), Monk(D8), PALADIN(D10), RANGER(D10),
    ROGUE(D8), SORCERER(D6), WARLOCK(D8), WIZARD(D6);

    private DieType hitDie;

    CharacterClass(DieType hitDie) {
        this.hitDie = hitDie;
    }
}
