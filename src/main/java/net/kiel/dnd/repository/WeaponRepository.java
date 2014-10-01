package net.kiel.dnd.repository;

import java.util.List;

import net.kiel.dnd.model.Weapon;

import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository {
    List<Weapon> selectByCharacter(Integer characterId);
}
