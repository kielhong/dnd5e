package net.kiel.dnd.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import net.kiel.dnd.Application;
import net.kiel.dnd.model.Character;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class CharacterServiceTest {
    @Autowired 
    private CharacterService characterService;
    
    @Test
    public void testGetAll() {
        List<Character> characters = characterService.findAll();
        
        assertNotNull(characters);
        assertTrue(characters.size() > 0);
    }
    
    @Test
    public void testGet() {
        final Integer characterId = 1;
        Character character = characterService.findById(characterId);
        
        assertNotNull(character);
    }
}
