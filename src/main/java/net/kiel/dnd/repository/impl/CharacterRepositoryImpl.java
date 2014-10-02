package net.kiel.dnd.repository.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.kiel.dnd.model.Character;
import net.kiel.dnd.repository.CharacterRepository;

@Repository
@Transactional
public class CharacterRepositoryImpl implements CharacterRepository {
    @Inject
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Character> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Character.class);
        return (List<Character>)cr.list();
    }

    @Override
    public Character select(Integer characterId) {
        Session session = sessionFactory.getCurrentSession();
        
        return (Character)session.get(Character.class, characterId);
    }
}