package net.kiel.dnd.repository;

import net.kiel.dnd.model.Proficiency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kiel on 2016. 5. 2..
 */
public interface ProficiencyRepository extends JpaRepository<Proficiency, Integer> {
}
