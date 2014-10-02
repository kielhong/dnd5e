package net.kiel.dnd.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.kiel.dnd.config.RepositoryConfig;
import net.kiel.dnd.config.TestRepositoryConfig;
import net.kiel.dnd.model.SavingThrow;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestRepositoryConfig.class})
@Transactional
public class SavingThrowRepositoryTest {
    @Inject
    private SessionFactory sessionFactory;
    
    @Test
    public void testListAll() {
        List savingThrows = sessionFactory.getCurrentSession().createCriteria(SavingThrow.class).list();
        
        assertNotNull(savingThrows);
    } 
}
