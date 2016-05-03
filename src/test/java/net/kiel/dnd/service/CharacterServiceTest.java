package net.kiel.dnd.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import net.kiel.dnd.model.Character;
import net.kiel.dnd.model.CharacterClass;
import net.kiel.dnd.model.Race;
import net.kiel.dnd.repository.CharacterRepository;
import net.kiel.dnd.service.impl.CharacterServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterServiceTest {
    @MockBean
    private CharacterRepository characterRepository;

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
        characterService = new CharacterServiceImpl(characterRepository);

        List<Character> characters = characterService.getList();

        assertThat(characters).isNotNull();
        assertThat(characters.size()).isEqualTo(2);
        then(characterRepository).should().findAll();
    }
    
    @Test
    public void testGet() {
        given(characterRepository.findOne(1L)).willReturn(character);
        characterService = new CharacterServiceImpl(characterRepository);

        Character testCharacter = characterService.findById(1L);

        assertThat(testCharacter).isNotNull();
        assertThat(testCharacter.getRace().getName()).isEqualTo("Human");
    }
}
