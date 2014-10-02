package net.kiel.dnd.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.kiel.dnd.config.RepositoryConfig;
import net.kiel.dnd.config.TestRepositoryConfig;
import net.kiel.dnd.model.Ability;
import net.kiel.dnd.model.Character;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestRepositoryConfig.class})
@Transactional
public class CharacterRepositoryTest {
    @Inject 
    private CharacterRepository characterRepository;
    
    @Test
    public void testSelectAll() {
        List<Character> characters = characterRepository.selectAll();
        
        assertNotNull(characters);
        assertTrue(characters.size() > 0);
    }
    
    @Test
    public void testSelect() {
        final Integer characterId = 1;
        Character character = characterRepository.select(characterId);
        
        assertNotNull(character);
//        assertNotNull(character.getAbilities());
        System.out.println(character.getAbilities());
//        assertEquals(6, character.getAbilities().size());
//        for (Ability ab : character.getAbilities()) {
//            System.out.println(ab.getType() + "," + ab.getScore() + "," + ab.getModifier());
//            if (ab.getScore() >= 10 && ab.getScore() <= 11) {
//                assertEquals(Integer.valueOf(0), ab.getModifier());
//            }
//        }
//        assertEquals(Ability.AbilityType.STRENGTH, character.getAbility(Ability.AbilityType.STRENGTH).getType()); // get 
//        
//        Iterator<Ability> iterAbility = character.getAbilities().iterator();
//        assertEquals(Ability.AbilityType.STRENGTH, iterAbility.next().getType()); // order by test
    }  
}
