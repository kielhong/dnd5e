package net.kiel.dnd.service;

import java.util.List;

import net.kiel.dnd.model.Character;

public interface CharacterService {
    List<Character> getList();

    Character findById(Long id);
}
