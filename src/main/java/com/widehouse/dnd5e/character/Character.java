package com.widehouse.dnd5e.character;

import lombok.Builder;

@Builder(buildMethodName = "create")
public class Character {
    private String characterName;
    private CharacterClass characterClass;
    private Race race;
    private Alignment alignment;
}
