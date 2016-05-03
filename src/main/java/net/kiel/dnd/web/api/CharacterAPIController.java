package net.kiel.dnd.web.api;

import net.kiel.dnd.model.Character;
import net.kiel.dnd.service.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

//@RestController
@RequestMapping("/api/character")
@Api(value="character", description="Character API")
public class CharacterAPIController {
    @Autowired
    private CharacterService characterService;
    
    @RequestMapping(value = "/{characterId}", method = RequestMethod.GET)
    @ApiOperation(value="character detail")
    public Character detail(
            @ApiParam(value="Id of character", required=true)
            @PathVariable Long characterId) {
        Character character = characterService.findById(characterId);


        return character;
    }

}
