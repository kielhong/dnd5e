package net.kiel.dnd.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import net.kiel.dnd.domain.Character;
import net.kiel.dnd.domain.CharacterClass;
import net.kiel.dnd.domain.Race;
import net.kiel.dnd.service.CharacterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @Before
    public void setUp() {
        Character character;
        character = new Character();
        character.setRace(new Race("Human"));
        character.setCharacterClass(new CharacterClass("Fighter"));
        character.setXp(0);
        character.setLevel(1);
        given(characterService.getCharacter(1L)).willReturn(character);
    }

    @Test
    public void testDetail() throws Exception {
        mockMvc.perform(get("/characters/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(CharacterController.class))
                .andExpect(handler().methodName("detail"))
                .andExpect(view().name("character"));
    }
    

}
