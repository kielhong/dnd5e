package net.kiel.dnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.kiel.dnd.model.Ability.AbilityType;
import lombok.Data;

@Data
public class Skill {
    private Integer skillId;
    
    @JsonIgnore
    private Integer characterId;

    @JsonIgnore
    private Character character;
    
    private Integer skillTypeId;
    
    private String name;

    private AbilityType abilityType;
    
    private Boolean proficiency;
    
    public Integer getValue() {        
        return character.getAbility(abilityType).getModifier() + (proficiency ? character.getProficiencyBonus() : 0); 
    }
}
