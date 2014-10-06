package net.kiel.dnd.service;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import net.kiel.dnd.config.WebConfig;
import net.kiel.dnd.model.Character;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class })
public class CharacterServiceTest {
    @Inject private CharacterService characterService;
    
    
    @Test
    public void testGet() {
        final Integer characterId = 1;
        Character character = characterService.get(characterId);
        
        System.out.print(character);
        assertNotNull(character);
        
//        List<SavingThrow> savingThrows = character.getSavingThrows();
//        for (SavingThrow savingThrow : savingThrows) {
//            Integer expect = character.getAbility(savingThrow.getAbilityType()).getModifier() + (savingThrow.getProficiency() ? character.getProficiencyBonus() : 0);
//            assertEquals(expect, savingThrow.getValue());
//        }
    }
}
