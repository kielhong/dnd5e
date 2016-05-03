package net.kiel.dnd.repository;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import net.kiel.dnd.model.Character;
import net.kiel.dnd.model.CharacterClass;
import net.kiel.dnd.model.Race;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Slf4j
public class CharacterRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired 
    private CharacterRepository characterRepository;

    Character givenCharacter;

    @Before
    public void setUp() {
        Race race = new Race("Dwarf");
        entityManager.persist(race);
        CharacterClass characterClass = new CharacterClass("Fighter");
        entityManager.persist(characterClass);

        givenCharacter = new Character();
        givenCharacter.setName("test name");
        givenCharacter.setPlayerName("test player name");
        givenCharacter.setRace(race);
        givenCharacter.setCharacterClass(characterClass);
        givenCharacter.setLevel(1);

        characterRepository.save(givenCharacter);
    }


    @Test
    public void findAllShouldReturnCharacterList() {
        List<Character> characters = characterRepository.findAll();

        assertThat(characters).isNotNull();
        assertThat(characters.size()).isGreaterThan(0);
    }
    
    @Test
    public void findOneShouldReturnCharacter() {
        Character character = characterRepository.findOne(givenCharacter.getId());

        assertThat(character).isNotNull();
        assertThat(character.getRace().getName()).isEqualTo("Dwarf");
        assertThat(character.getCharacterClass().getName()).isEqualTo("Fighter");
        assertThat(character.getLevel()).isEqualTo(1);

//        assertEquals(Integer.valueOf(2), character.getProficiency().getBonus());
//        assertEquals(Integer.valueOf(1), character.getInitiative());
//        assertEquals(6, character.getAbilities().size());
//        assertEquals(18, character.getSkills().size());
        
//        Ability ability = character.getAbilities().iterator().next();
//        assertEquals(AbilityType.STRENGTH, ability.getType());  // first ability must be STR
//        assertEquals(Integer.valueOf(17), ability.getScore());
//        assertEquals(Integer.valueOf(3), ability.getModifier());
//        assertEquals(5, ability.getModifier() +
// (ability.getSavingThrow().isProficiency() ? character.getProficiency().getBonus() : 0));
//        assertEquals(1, ability.getSkills().size());
//
//        assertTrue(character.getWeapons().size() > 0);
//        assertEquals("Warhammer", character.getWeapons().iterator().next().getWeapon().getName());
    }  
}
