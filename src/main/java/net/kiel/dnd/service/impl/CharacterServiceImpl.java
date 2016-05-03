package net.kiel.dnd.service.impl;

import net.kiel.dnd.model.Character;
import net.kiel.dnd.repository.CharacterRepository;
import net.kiel.dnd.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {
    CharacterRepository characterRepository;

    public CharacterServiceImpl() {

    }

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<Character> getList() {
        List<Character> characters = characterRepository.findAll();
        
        return characters;
    }
    
    @Override
    public Character getCharacter(Long characterId) {
        Character character =  characterRepository.findOne(characterId);
        
        return character;
    }

    @Override
    public void earnXp(Character character, Integer xp) {
        character.setXp(character.getXp() + xp);
        if (character.getXp() >= 300) {
            character.setLevel(character.getLevel() + 1);
        }
    }
}