package net.kiel.dnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import net.kiel.dnd.model.Ability.AbilityType;


@Data
public class SavingThrow {
    private Integer savingThrowId;
    
    @JsonIgnore
    private Integer characterId;
    
    @JsonIgnore
    private Character character;
    
    private Boolean proficiency;
        
    private AbilityType abilityType;
    
    
    public Integer getValue() {        
        return character.getAbility(abilityType).getModifier() + (proficiency ? character.getProficiencyBonus() : 0); 
    }


    @Override
    public String toString() {
        return "SavingThrow [savingThrowId=" + savingThrowId + ", characterId=" + characterId + ", proficiency="
                + proficiency + ", abilityType=" + abilityType + "]";
    }
}
