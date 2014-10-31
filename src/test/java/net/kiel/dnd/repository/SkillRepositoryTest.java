package net.kiel.dnd.repository;

import net.kiel.dnd.config.RepositoryConfig;
import net.kiel.dnd.config.TestRepositoryConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestRepositoryConfig.class})
public class SkillRepositoryTest {
    //@Inject private SkillRepository skillRepository;
    
    @Test
    public void testSelectByCharacter() {
//        final Integer characterId = 1;
//        
//        List<Skill> skills = skillRepository.selectByCharacter(characterId);
//        
//        assertEquals(18, skills.size());        
    } 
}