package net.kiel.dnd.domain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kiel on 2016. 5. 4..
 */
public interface LevelRepository extends JpaRepository<Level, Integer> {
    @Cacheable("level")
    List<Level> findAll();
}
