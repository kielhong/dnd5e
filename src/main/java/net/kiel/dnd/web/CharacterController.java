package net.kiel.dnd.web;

import net.kiel.dnd.domain.Character;
import net.kiel.dnd.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CharacterController {
    @Autowired 
    private CharacterService characterService;

    /**
     * index
     * @param model Model
     * @return index
     */
    @GetMapping(value = {"/", "/characters"})
    public String index(Model model) {
        List<Character> characters = characterService.getList();
        
        model.addAttribute("characters", characters);
        
        return "index";
    }

    /**
     * one character info
     * @param id character id
     * @param model Model
     * @return character one
     */
    @RequestMapping(value = "/characters/{id}", method = RequestMethod.GET)
    public String detail(
            @PathVariable Long id,
            Model model) {        
        Character character = characterService.getCharacter(id);
        
        model.addAttribute("character", character);

        return "character";
    }
}