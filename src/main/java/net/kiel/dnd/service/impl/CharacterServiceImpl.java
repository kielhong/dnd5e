package net.kiel.dnd.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import net.kiel.dnd.model.Character;
import net.kiel.dnd.repository.CharacterRepository;
import net.kiel.dnd.service.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {
    @Autowired 
    CharacterRepository characterRepository;
    
    @Override
    public List<Character> findAll() {
        List<Character> characters = characterRepository.selectAll();
        
        return characters;
    }
    
    @Override
    public Character findById(Integer characterId) {
        Character character =  characterRepository.select(characterId);
        
        return character;
    }
}