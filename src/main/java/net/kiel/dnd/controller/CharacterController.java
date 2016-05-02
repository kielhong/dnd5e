package net.kiel.dnd.controller;

import java.util.List;

import javax.transaction.Transactional;

import net.kiel.dnd.model.Character;
import net.kiel.dnd.service.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
public class CharacterController {
    @Autowired 
    private CharacterService characterService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<Character> characters = characterService.findAll();        
        
        model.addAttribute("characters", characters);
        
        return "index";
    }
    
    @RequestMapping(value = "/character/{characterId}", method = RequestMethod.GET)
    public String detail(
            @PathVariable Long characterId,
            Model model) {        
        Character character = characterService.findById(characterId);
        
        model.addAttribute("character", character);

        return "character";
    }
}