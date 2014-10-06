package net.kiel.dnd.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import net.kiel.dnd.config.RepositoryConfig;
import net.kiel.dnd.model.Ability;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class AbilityRepositoryTest {
    @Inject private AbilityRepository abilityRepository;
    
    @Test
    public void testSelectByCharacter() {
        final Integer characterId = 1;
        
        List<Ability> abilities = abilityRepository.selectByCharacter(characterId);
        
        assertEquals(6, abilities.size());        
    } 
}
