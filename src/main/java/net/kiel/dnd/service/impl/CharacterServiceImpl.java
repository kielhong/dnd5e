package net.kiel.dnd.service.impl;

import com.google.common.collect.ImmutableMap;

import lombok.extern.slf4j.Slf4j;
import net.kiel.dnd.domain.Character;
import net.kiel.dnd.domain.CharacterRepository;
import net.kiel.dnd.domain.Level;
import net.kiel.dnd.domain.LevelRepository;
import net.kiel.dnd.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    LevelRepository levelRepository;

    @Override
    public List<Character> getList() {
        List<Character> characters = characterRepository.findAll();

        return characters;
    }

    @Override
    public Character getCharacter(Long characterId) {
        Character character = characterRepository.findOne(characterId);

        return character;
    }

    @Override
    public void earnXp(Character character, Integer xp) {
        character.setXp(character.getXp() + xp);

        List<Level> levels = levelRepository.findAll();

        for (int i = 0; i < levels.size() - 1; i++) {
            if (levels.get(i).getXp() <= character.getXp()
                    && character.getXp() < levels.get(i + 1).getXp()) {
                character.setLevel(levels.get(i).getLevel());
                break;
            }
        }

        characterRepository.save(character);
    }

    @Override
    public Character changeLevel(Character character, Integer level) {
        character.setLevel(level);
        Level levelTable = levelRepository.findOne(level);
        character.setProficiencyBonus(levelTable.getProficiencyBonus());
        characterRepository.save(character);

        return character;
    }
}