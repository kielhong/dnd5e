package net.kiel.dnd.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import net.kiel.dnd.model.Character;
import net.kiel.dnd.model.CharacterClass;
import net.kiel.dnd.model.Proficiency;
import net.kiel.dnd.model.Race;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CharacterRepositoryTest {    
    @Autowired 
    private CharacterRepository characterRepository;
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private CharacterClassRepository classRepository;
    @Autowired
    private ProficiencyRepository proficiencyRepository;

    Character givenCharacter;
    @Before
    public void setUp() {
        Race race = new Race(1, "Dwarf");
        raceRepository.save(race);
        CharacterClass characterClass = new CharacterClass(1, "Fighter");
        classRepository.save(characterClass);
        Proficiency proficiency = new Proficiency(1, 2);
        proficiencyRepository.save(proficiency);

        givenCharacter = new Character();
        givenCharacter.setName("character name");
        givenCharacter.setPlayerName("character player name");
//        character.setRace(race);
//        character.setCharacterClass(characterClass);
//        character.setProficiency(proficiency);

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

//        assertEquals("Dwarf", character.getRace().getName());
//        assertEquals("Fighter", character.getCharacterClass().getName());
//        assertEquals(Integer.valueOf(1), character.getLevel());
//        assertEquals(Integer.valueOf(2), character.getProficiency().getBonus());
//        assertEquals(Integer.valueOf(1), character.getInitiative());
//        assertEquals(6, character.getAbilities().size());
//        assertEquals(18, character.getSkills().size());
        
//        Ability ability = character.getAbilities().iterator().next();
//        assertEquals(AbilityType.STRENGTH, ability.getType());  // first ability must be STR
//        assertEquals(Integer.valueOf(17), ability.getScore());
//        assertEquals(Integer.valueOf(3), ability.getModifier());
//        assertEquals(5, ability.getModifier() + (ability.getSavingThrow().isProficiency() ? character.getProficiency().getBonus() : 0));
//        assertEquals(1, ability.getSkills().size());
//
//        assertTrue(character.getWeapons().size() > 0);
//        assertEquals("Warhammer", character.getWeapons().iterator().next().getWeapon().getName());
    }  
}
