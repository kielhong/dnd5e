package net.kiel.dnd.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Ability {
    private Integer id;
    
    //private Integer characterId;
    
    private AbilityType type;
    
    private Integer score;
    
    private Integer modifier;
    
    public enum AbilityType {
        UNKNOWN(0, "UNKNOWN"), 
        STRENGTH(1, "STR"), 
        DEXTERITY(2, "DEX"), 
        CONSTITUTION(3, "CON"), 
        INTELLIGENCE(4, "INT"), 
        WISDOM(5, "WIS"), 
        CHARISMA(6, "CHA");
        
        @Getter private final int value;
        @Getter private final String shorten;
        
        AbilityType(int value, String shorten) {
            this.value = value;
            this.shorten = shorten;
        }
    }
}