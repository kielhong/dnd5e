package net.kiel.dnd.repository;

import java.util.List;

import net.kiel.dnd.model.SavingThrow;

import org.springframework.stereotype.Repository;

@Repository
public interface SavingThrowRepository {
    List<SavingThrow> selectByCharacter(Integer characterId);
}
