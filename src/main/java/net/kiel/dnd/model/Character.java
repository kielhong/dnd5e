package net.kiel.dnd.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.kiel.dnd.model.Ability.AbilityType;
import lombok.Data;

@Data
public class Character {
    Integer id;
    
    String playerName;
    
    String name;
    
    String race;
    
    @JsonProperty("class")
    String characterClass;
    
    Integer level;
    
    Integer xp;
    
    Integer proficiencyBonus;
    
    private List<Ability> abilities;
    
    public Ability getAbility(AbilityType type) {
        for (Ability ability : getAbilities()) {
            if (ability.getType() == type) {
                return ability;
            }
        }
        
        return null;
    }
    
    private List<SavingThrow> savingThrows;
    
    private List<Skill> skills;
    
    private List<Weapon> weapons;
    
    String background;
    
    String alignment;
    
    Integer armorClass;
    
    public Integer getInitiative() {
        return getAbility(AbilityType.DEXTERITY).getModifier();
    }
    
    Integer speed;
    
    Integer hpMax;
    
    Integer hpCurrent;
    
    Date createdDate;

    @Override
    public String toString() {
        return "Character [id=" + id + ", playerName=" + playerName + ", name=" + name + ", race="
                + race + ", characterClass=" + characterClass + ", level=" + level + ", xp=" + xp
                + ", proficiencyBonus=" + proficiencyBonus + ", background=" + background + ", alignment=" + alignment
                + ", armorClass=" + armorClass + ", speed=" + speed + ", hpMax=" + hpMax + ", hpCurrent=" + hpCurrent
                + ", createdDate=" + createdDate + "]";
    }
    
    
}
