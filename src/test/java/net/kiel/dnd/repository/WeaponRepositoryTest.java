package net.kiel.dnd.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import net.kiel.dnd.Application;
import net.kiel.dnd.model.CharacterWeapon;
import net.kiel.dnd.model.Weapon;

import org.hibernate.Query;
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
public class WeaponRepositoryTest {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Test
    public void testSelectAll() {
        Session session = sessionFactory.getCurrentSession();
        
        @SuppressWarnings("unchecked")
        List<Weapon> weapons = session.createCriteria(Weapon.class).list();
        
        assertNotNull(weapons);
        assertTrue(weapons.size() > 0);
        

    } 
    
    @Test
    public void testSelectByCharacter() {
        Session session = sessionFactory.getCurrentSession();
        
        Query query = session.createQuery("from CharacterWeapon as cw where cw.character.id = :characterId")
                                .setParameter("characterId", 1);

        @SuppressWarnings("unchecked")
        List<CharacterWeapon> weapons = (List<CharacterWeapon>)query.list();
        
        assertNotNull(weapons);
        
        System.out.println(weapons);
    }
}