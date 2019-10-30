package com.widehouse.dnd5e.character;

import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(buildMethodName = "create")
public class Character {
    private String characterName;
    private CharacterClass characterClass;
    private Race race;
    private Alignment alignment;
    @Builder.Default
    private Integer xp = 0;
    @Builder.Default
    private Integer level = 1;

    private Ability ability;

    public void setAbilities(int str, int dex, int con, int inte, int wis, int chr) {
        this.ability = new Ability(Stat.of(str), Stat.of(dex), Stat.of(con), Stat.of(inte), Stat.of(wis), Stat.of(chr));
    }

    public void earnXp(int xp) {
        this.xp += xp;

        advanceLevel();
    }

    private void advanceLevel() {
        List<Integer> table = Arrays.asList(0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000, 64000, 85000,
                100000, 120000, 140000, 165000, 195000, 225000, 265000, 305000, 355000);
        for (int i = 0; i < table.size(); i++) {
            if (this.xp >= table.get(i)) {
                this.level = i + 1;
            } else {
                break;
            }
        }
    }
}
