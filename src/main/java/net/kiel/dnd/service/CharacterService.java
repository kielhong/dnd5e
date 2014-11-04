package net.kiel.dnd.service;

import java.util.List;

import net.kiel.dnd.model.Character;

public interface CharacterService {
    List<Character> findAll();
    Character findById(Integer id);
}
