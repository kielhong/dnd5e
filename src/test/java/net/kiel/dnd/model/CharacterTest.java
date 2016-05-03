package net.kiel.dnd.model;

import static org.assertj.core.api.Assertions.assertThat;

import net.kiel.dnd.service.impl.CharacterServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kiel on 2016. 5. 3..
 */
public class CharacterTest {
    Character character;

    @Before
    public void setUp() {
        character = new Character();
        character.setRace(new Race("Human"));
        character.setCharacterClass(new CharacterClass("Fighter"));
        character.setXp(0);
        character.setLevel(1);
    }

    @Test
    public void testLevelUp() {
        character.earnXp(300);
        assertThat(character.getLevel()).isEqualTo(2);
    }
}
