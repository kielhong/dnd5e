package net.kiel.dnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Weapon {
    private Integer weaponId;
    
    private String name;
    
    @JsonIgnore
    private Integer characterId;
    
    @JsonIgnore
    private Character character;
    
    private Integer weaponType;
    
    private Ability ability;
    
    private Integer cost;
    
    private String damage;
    
    private String damageType;
    
    private Boolean proficiency;
    
    public Integer getAttackBonus() {
        Ability.AbilityType abilityType = null;
        if (weaponType == 2 || weaponType == 4) {
            abilityType = Ability.AbilityType.DEXTERITY;
        } else {
            abilityType = Ability.AbilityType.STRENGTH;
        }
        
        return character.getAbility(abilityType).getModifier() + (proficiency ? character.getProficiencyBonus() : 0); 
    }

}
