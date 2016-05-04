package net.kiel.dnd.web.api;

import io.swagger.annotations.Api;
import net.kiel.dnd.domain.Character;
import net.kiel.dnd.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@Api(tags = "character", description = "Character Operation")
public class ApiCharacterController {
    @Autowired
    private CharacterService characterService;

    /**
     * get character list
     * @return List of Character
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public List<Character> list() {
        List<Character> characters = characterService.getList();

        return characters;
    }

    /**
     * get one character info
     * @param id id of character
     * @return info
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Character detail(@PathVariable Long id) {
        Character character = characterService.getCharacter(id);

        return character;
    }

}
