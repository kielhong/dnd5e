package net.kiel.dnd.repository;

import java.util.List;

import net.kiel.dnd.model.Skill;

import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository {
    List<Skill> selectByCharacter(Integer characterId);
}
