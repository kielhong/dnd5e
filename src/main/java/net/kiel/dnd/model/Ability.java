package net.kiel.dnd.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ability_type")
    private AbilityType type;
    
    @ManyToOne
    @JoinColumn(name = "score", nullable = false)
    private AbilityModifier abilityModifier;
  
    public Integer getScore() {
        return abilityModifier.getScore();
    }
    
    public Integer getModifier() {
        return abilityModifier.getModifier();
    }
    
    @OneToOne(mappedBy = "ability")
    private SavingThrow savingThrow;
    
    @OneToMany(mappedBy = "ability")
    private Set<Skill> skills;
    
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

    @Override
    public String toString() {
        return "Ability [id=" + id + ", type=" + type + ", abilityModifier=" + abilityModifier + "]";
    }
    
    
       
}