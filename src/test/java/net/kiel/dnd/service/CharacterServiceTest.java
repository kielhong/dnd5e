package net.kiel.dnd.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.kiel.dnd.model.Character;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CharacterServiceTest {
    @Autowired 
    private CharacterService characterService;
    
    @Test
    public void testGetAll() {
        List<Character> characters = characterService.getAll();
        
        assertNotNull(characters);
        assertTrue(characters.size() > 0);
    }
    
    @Test
    public void testGet() {
        final Integer characterId = 1;
        Character character = characterService.get(characterId);
        
        assertNotNull(character);
    }
}
