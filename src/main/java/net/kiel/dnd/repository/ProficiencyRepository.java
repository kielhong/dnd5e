package net.kiel.dnd.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProficiencyRepository {
    Integer selectBonus(int level);
}
