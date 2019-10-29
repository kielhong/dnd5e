package com.widehouse.dnd5e.character;

import lombok.Builder;

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
}
