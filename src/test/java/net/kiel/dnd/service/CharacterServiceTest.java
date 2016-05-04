package net.kiel.dnd.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import net.kiel.dnd.domain.Character;
import net.kiel.dnd.domain.CharacterClass;
import net.kiel.dnd.domain.CharacterRepository;
import net.kiel.dnd.domain.Level;
import net.kiel.dnd.domain.LevelRepository;
import net.kiel.dnd.domain.Race;
import net.kiel.dnd.service.impl.CharacterServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterServiceTest {
    @MockBean
    private CharacterRepository characterRepository;
    @MockBean
    private LevelRepository levelRepository;

    @Autowired
    private CharacterService characterService;

    Character character;

    @Before
    public void setUp() {
        character = new Character();
        character.setRace(new Race("Human"));
        character.setCharacterClass(new CharacterClass("Fighter"));
        character.setXp(0);
        character.setLevel(1);
    }

    @Test
    public void testGetList() {
        given(characterRepository.findAll()).willReturn(Arrays.asList(character, new Character()));

        List<Character> characters = characterService.getList();

        assertThat(characters).isNotNull();
        assertThat(characters.size()).isEqualTo(2);
        then(characterRepository).should().findAll();
    }
    
    @Test
    public void testGet() {
        given(characterRepository.findOne(1L)).willReturn(character);

        Character testCharacter = characterService.getCharacter(1L);

        assertThat(testCharacter).isNotNull();
        assertThat(testCharacter.getRace().getName()).isEqualTo("Human");
    }

    @Test
    public void earnEnoughXpShouldLevelUp() {
        given(levelRepository.findAll()).willReturn(
                Arrays.asList(new Level(1, 0, 2), new Level(2, 300, 2), new Level(3, 900, 2), new Level(4, 2700, 2)));

        // xp = 10
        characterService.earnXp(character, 10);
        assertThat(character.getLevel()).isEqualTo(1);
        then(characterRepository).should().save(character);

        // xp = 300
        characterService.earnXp(character, 290);
        assertThat(character.getLevel()).isEqualTo(2);

        // xp = 910
        characterService.earnXp(character, 610);
        assertThat(character.getLevel()).isEqualTo(3);

        // loose xp, xp = 890
        characterService.earnXp(character, -20);
        assertThat(character.getLevel()).isEqualTo(2);
    }

    @Test
    public void changeLevelShouldChangeProficiencyBonus() {
        given(levelRepository.findOne(1)).willReturn(new Level(1, 0, 2));
        given(levelRepository.findOne(5)).willReturn(new Level(5, 65000, 3));

        Character changedCharacter = characterService.changeLevel(character, 1);
        assertThat(changedCharacter.getProficiencyBonus()).isEqualTo(2);
        then(characterRepository).should().save(character);

        changedCharacter = characterService.changeLevel(character, 5);
        assertThat(changedCharacter.getProficiencyBonus()).isEqualTo(3);
    }

    @Configuration
    @Import(CharacterServiceImpl.class)
    static class Config {
    }
}
