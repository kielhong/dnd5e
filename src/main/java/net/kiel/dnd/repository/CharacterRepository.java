package net.kiel.dnd.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.kiel.dnd.model.Character;

@Repository
public interface CharacterRepository {
    public List<Character> selectAll();
    
    public Character select(Integer id);
}
