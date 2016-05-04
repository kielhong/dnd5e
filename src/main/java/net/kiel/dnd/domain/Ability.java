package net.kiel.dnd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
@AllArgsConstructor
public class Ability {
    public Ability(AbilityType type, Integer score) {
        this.type = type;
        this.score = score;
    }

    @Id
    @GeneratedValue
    private Integer id;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ability_type")
    private AbilityType type;

    @Min(0)
    @Max(30)
    private Integer score;

    public Integer getModifier() {
        return score / 2 - 5;
    }

    public enum AbilityType {
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