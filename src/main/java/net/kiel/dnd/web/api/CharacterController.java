package net.kiel.dnd.web.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import net.kiel.dnd.model.Character;
import net.kiel.dnd.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RestController
@RequestMapping("/api/characters")
@Api(value = "character", description = "Character API")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    /**
     * get one character info
     * @param id id of character
     * @return info
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "character detail")
    public Character detail(
            @ApiParam(value = "Id of character", required = true)
            @PathVariable Long id) {
        Character character = characterService.getCharacter(id);


        return character;
    }

}
