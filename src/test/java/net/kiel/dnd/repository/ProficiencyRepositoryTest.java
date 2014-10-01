package net.kiel.dnd.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import net.kiel.dnd.config.RepositoryConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class ProficiencyRepositoryTest {
    @Inject private ProficiencyRepository proficiencyRepository;
    
    
    @Test
    public void testSelect() {        
        assertEquals(Integer.valueOf(2), proficiencyRepository.selectBonus(1));
        assertEquals(Integer.valueOf(2), proficiencyRepository.selectBonus(4));
        assertEquals(Integer.valueOf(3), proficiencyRepository.selectBonus(8));
        assertEquals(Integer.valueOf(5), proficiencyRepository.selectBonus(13));
        assertEquals(Integer.valueOf(6), proficiencyRepository.selectBonus(20));
        assertNull(proficiencyRepository.selectBonus(21));
    }
  
}
