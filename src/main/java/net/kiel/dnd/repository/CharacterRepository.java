package net.kiel.dnd.repository;

import net.kiel.dnd.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
