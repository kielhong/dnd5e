package net.kiel.dnd.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import net.kiel.dnd.Application;
import net.kiel.dnd.model.SkillType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class SkillRepositoryTest {
    @Autowired
    private SessionFactory sessionFactory;
     
    
    @Test
    public void testSkillType() {
        Session session = sessionFactory.getCurrentSession();
        
        @SuppressWarnings("unchecked")
        List<SkillType> skillTypes = session.createCriteria(SkillType.class).list();
        
        assertEquals(18, skillTypes.size());
    }
}