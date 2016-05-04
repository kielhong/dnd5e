package net.kiel.dnd.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableSet;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@DataJpaTest
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
        List<Ability> abilities = Arrays.asList(
                        new Ability(Ability.AbilityType.STRENGTH, 10),
                        new Ability(Ability.AbilityType.DEXTERITY, 13),
                        new Ability(Ability.AbilityType.CONSTITUTION, 14),
                        new Ability(Ability.AbilityType.INTELLIGENCE, 8),
                        new Ability(Ability.AbilityType.WISDOM, 17),
                        new Ability(Ability.AbilityType.CHARISMA, 7));
        for (Ability ability : abilities) {
            entityManager.persist(ability);
        }

        givenCharacter = new Character();
        givenCharacter.setName("test name");
        givenCharacter.setPlayerName("test player name");
        givenCharacter.setRace(race);
        givenCharacter.setCharacterClass(characterClass);
        givenCharacter.setLevel(1);
        givenCharacter.setAbilities(abilities);

        entityManager.persist(givenCharacter);
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
//        assertEquals(18, character.getSkills().size());

//        assertEquals(5, ability.getModifier() +
// (ability.getSavingThrow().isProficiency() ? character.getProficiency().getBonus() : 0));
//        assertEquals(1, ability.getSkills().size());
//
//        assertEquals("Warhammer", character.getWeapons().iterator().next().getWeapon().getName());
    }

    @Test
    public void characterShouldHaveAbilities() {
        // When
        Character character = characterRepository.findOne(givenCharacter.getId());

        // Then
        assertThat(character.getAbilities().size())
                .isEqualTo(6);
        // 0 = str, 4 = wis, 5 = chr
        assertThat(character.getAbilities().get(0).getType())
                .isEqualTo(Ability.AbilityType.STRENGTH);
        assertThat(character.getAbilities().get(5).getType())
                .isEqualTo(Ability.AbilityType.CHARISMA);
        assertThat(character.getAbilities().get(0).getModifier())
                .isEqualTo(0);
        assertThat(character.getAbilities().get(4).getModifier())
                .isEqualTo(3);
        assertThat(character.getAbilities().get(5).getModifier())
                .isEqualTo(-2);
    }
}
