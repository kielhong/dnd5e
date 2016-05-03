package net.kiel.dnd.service;

import net.kiel.dnd.domain.Character;

import java.util.List;

public interface CharacterService {
    List<Character> getList();

    Character getCharacter(Long id);

    void earnXp(Character character, Integer xp);
}
