package net.kiel.dnd.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import lombok.Data;
import net.kiel.dnd.model.Ability.AbilityType;

@Entity
@Data
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String playerName;
    
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "raceId", nullable = false)
    private Race race;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classId", nullable = false)
    private Class characterClass;
    
    private Integer level;
    
    private Integer xp;
    
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "level")
//    private Proficiency proficiency;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "characterId", nullable = false)
    @OrderBy("abilityType")
    private Set<Ability> abilities;
    
    @Transient
    private Map<AbilityType, Ability> abilitiesMap = new HashMap<>();
    
    private void initAbilityMap() {
        for (Ability ability : getAbilities()) {
            abilitiesMap.put(ability.getType(), ability);
        }
    }
    
    public Ability getAbility(AbilityType type) {
        if (abilitiesMap.isEmpty()) {
            initAbilityMap();
        }
        
        return abilitiesMap.get(type);
    }
    
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "character_id")
//    private List<SavingThrow> savingThrows;
//    
//    private List<Skill> skills;
//    
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "character_id")
//    private Set<Weapon> weapons;
    
    private String background;
    
    private String alignment;
    
    private Integer armorClass;
    
    public Integer getInitiative() {
        return getAbility(AbilityType.DEXTERITY).getModifier();
    }
    
    private Integer speed;
    
    @Column(name="hpMax")
    private Integer hpMax;
    
    @Column(name="hpCurrent")
    private Integer hpCurrent;
    
    @Column(name="createdDateTime")
    private Date createdDateTime;

    @Override
    public String toString() {
        return "Character [id=" + id + ", playerName=" + playerName + ", name=" + name + ", race=" + race;
//        return "Character [id=" + id + ", playerName=" + playerName + ", name=" + name + ", race=" + race
//                + ", characterClass=" + characterClass + ", level=" + level + ", xp=" + xp + ", proficiency="
//                + proficiency + ", background=" + background + ", alignment=" + alignment + ", armorClass="
//                + armorClass + ", speed=" + speed + ", hpMax=" + hpMax + ", hpCurrent=" + hpCurrent + ", createdDate="
//                + createdDateTime + "]";
    }
}
