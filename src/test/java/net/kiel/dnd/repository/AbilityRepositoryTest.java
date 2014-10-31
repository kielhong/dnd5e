package net.kiel.dnd.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import net.kiel.dnd.config.RepositoryConfig;
import net.kiel.dnd.config.TestRepositoryConfig;
import net.kiel.dnd.model.Ability;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestRepositoryConfig.class})
@Transactional
public class AbilityRepositoryTest {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Test
    public void testListAll() {
        @SuppressWarnings("unchecked")
        List<Ability> abilities = (List<Ability>)sessionFactory.getCurrentSession().createCriteria(Ability.class).list();
        
        assertNotNull(abilities);
    }
    
    @Test
    public void testListByCharacter() {
        Integer characterId = 1;
        
//        @SuppressWarnings("unchecked")
//        List<Ability> abilities = (List<Ability>)sessionFactory.getCurrentSession().createQuery("FROM net.kiel.dnd.model.Ability WHERE character_id = :characterId")
//                                    .setParameter("characterId", characterId)
//                                    .list();
//        @SuppressWarnings("unchecked")
//        List<Ability> abilities = (List<Ability>)sessionFactory.getCurrentSession().createCriteria(Ability.class)
//                                    .createCriteria("character")
//                                    .add(Restrictions.eq("id", characterId))
//                                    .list();
        
//        assertNotNull(abilities);
//        for (Ability ability : abilities) {
//            System.out.println(ability.getType() + "," + ability.getSavingThrow().getProficiency() + "," + ability.getSavingThrow().getValue());
//            assertNotNull(ability.getSavingThrow());
//            if (ability.getSavingThrow().getProficiency()) {
//                Integer expected = ability.getModifier() + ability.getCharacter().getProficiency().getBonus();
//                assertEquals(expected, ability.getSavingThrow().getValue());
//            }
//        }
    }
}