package net.kiel.dnd.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import net.kiel.dnd.config.RepositoryConfig;
import net.kiel.dnd.model.Character;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class CharacterRepositoryTest {
    @Inject private CharacterRepository characterRepository;
    
    @Test
    public void testSelectAll() {
        List<Character> characters = characterRepository.selectAll();
        
        assertNotNull(characters);
    }
    
    @Test
    public void testSelect() {
        final Integer characterId = 1;
        Character character = characterRepository.select(characterId);
        
        assertNotNull(character);
        assertEquals(characterId, character.getId());
    }
  
}
