package net.kiel.dnd.service.impl;

import java.util.List;

import javax.inject.Inject;

import net.kiel.dnd.model.Character;
import net.kiel.dnd.model.SavingThrow;
import net.kiel.dnd.model.Skill;
import net.kiel.dnd.model.Weapon;
import net.kiel.dnd.repository.CharacterRepository;
import net.kiel.dnd.repository.ProficiencyRepository;
import net.kiel.dnd.repository.SavingThrowRepository;
import net.kiel.dnd.repository.SkillRepository;
import net.kiel.dnd.repository.WeaponRepository;
import net.kiel.dnd.service.CharacterService;

import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Inject CharacterRepository characterRepository;
    @Inject ProficiencyRepository proficiencyRepository;
    @Inject SavingThrowRepository savingThrowRepository;
    @Inject SkillRepository skillRepository;
    @Inject WeaponRepository weaponRepository;
    
    @Override
    public List<Character> getAll() {
        List<Character> characters = characterRepository.selectAll();
        
        return characters;
    }
    
    @Override
    public Character get(Integer characterId) {
        Character character =  characterRepository.select(characterId);
        if (character != null) {
            character.setProficiencyBonus(proficiencyRepository.selectBonus(character.getLevel()));
            
            List<SavingThrow> savingThrows = savingThrowRepository.selectByCharacter(characterId);
            for (SavingThrow savingThrow : savingThrows) {
                savingThrow.setCharacter(character);
            }
            character.setSavingThrows(savingThrows);
            
            List<Skill> skills = skillRepository.selectByCharacter(characterId);
            for (Skill skill : skills) {
                skill.setCharacter(character);
            }
            character.setSkills(skills);
            
            List<Weapon> weapons = weaponRepository.selectByCharacter(characterId);
            for (Weapon weapon : weapons) {
                weapon.setCharacter(character);
            }
            character.setWeapons(weapons);
        }
        
        return character;
    }
}