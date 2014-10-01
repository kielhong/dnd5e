package net.kiel.dnd.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.kiel.dnd.model.Ability;

@Repository
public interface AbilityRepository {
    public List<Ability> selectAll(Integer characterId);
}
