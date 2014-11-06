package net.kiel.dnd.repository;

import java.util.List;

import net.kiel.dnd.model.Character;

public interface CharacterRepository {
    public List<Character> selectAll();
    
    public Character select(Integer id);
}
