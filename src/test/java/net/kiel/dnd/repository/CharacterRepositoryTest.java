package net.kiel.dnd.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import net.kiel.dnd.Application;
import net.kiel.dnd.model.Ability;
import net.kiel.dnd.model.Ability.AbilityType;
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
public class CharacterRepositoryTest {    
    @Autowired 
    private CharacterRepository characterRepository;
    
    @Test
    public void testSelectAll() {
        List<Character> characters = characterRepository.findAll();
        
        assertNotNull(characters);
        assertTrue(characters.size() > 0);
    }
    
    @Test
    public void testSelect() {
        final Integer id = 1;
        Character character = characterRepository.findOne(id);
        
        assertNotNull(character);
        assertEquals("Dwarf", character.getRace().getName());
        assertEquals("Fighter", character.getCharacterClass().getName());
        assertEquals(Integer.valueOf(1), character.getLevel());
        assertEquals(Integer.valueOf(2), character.getProficiency().getBonus());
        assertEquals(Integer.valueOf(1), character.getInitiative());
        assertEquals(6, character.getAbilities().size());
        assertEquals(18, character.getSkills().size());
        
        Ability ability = character.getAbilities().iterator().next();
        assertEquals(AbilityType.STRENGTH, ability.getType());  // first ability must be STR
        assertEquals(Integer.valueOf(17), ability.getScore());
        assertEquals(Integer.valueOf(3), ability.getModifier());
        assertEquals(5, ability.getModifier() + (ability.getSavingThrow().isProficiency() ? character.getProficiency().getBonus() : 0));
        assertEquals(1, ability.getSkills().size());
        
        assertTrue(character.getWeapons().size() > 0);
        assertEquals("Warhammer", character.getWeapons().iterator().next().getWeapon().getName());
    }  
}
