package net.kiel.dnd.repository;

import net.kiel.dnd.config.RepositoryConfig;
import net.kiel.dnd.config.TestRepositoryConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestRepositoryConfig.class})
public class ProficiencyRepositoryTest {
    @Test
    public void testSelect() {        
//        assertEquals(Integer.valueOf(2), proficiencyRepository.selectBonus(1));
//        assertEquals(Integer.valueOf(2), proficiencyRepository.selectBonus(4));
//        assertEquals(Integer.valueOf(3), proficiencyRepository.selectBonus(8));
//        assertEquals(Integer.valueOf(5), proficiencyRepository.selectBonus(13));
//        assertEquals(Integer.valueOf(6), proficiencyRepository.selectBonus(20));
//        assertNull(proficiencyRepository.selectBonus(21));
    }
  
}
