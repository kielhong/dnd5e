package net.kiel.dnd.service.impl;

import com.google.common.collect.ImmutableMap;

import lombok.extern.slf4j.Slf4j;
import net.kiel.dnd.model.Character;
import net.kiel.dnd.repository.CharacterRepository;
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
        Character character = characterRepository.findOne(characterId);

        return character;
    }

    @Override
    public void earnXp(Character character, Integer xp) {
        character.setXp(character.getXp() + xp);

        List<Integer> levels = xpTable.keySet().asList();
        for (int i = 0; i < levels.size(); i++) {
            Integer currentLevel = levels.get(i);
            Integer nextLevel = levels.get(i + 1);
            if (xpTable.get(currentLevel) <= character.getXp()
                    && xpTable.get(nextLevel) > character.getXp()) {
                character.setLevel(levels.get(i));
                break;
            }
        }

        characterRepository.save(character);
    }

    private static ImmutableMap<Integer, Integer> xpTable = new ImmutableMap.Builder<Integer, Integer>()
            .put(1, 0)
            .put(2, 300)
            .put(3, 900)
            .put(4, 2700)
            .put(5, 6500)
            .put(6, 14000)
            .put(7, 23000)
            .put(8, 34000)
            .put(9, 48000)
            .put(10, 64000)
            .put(20, 355000)
            .build();
}