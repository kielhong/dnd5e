package net.kiel.dnd.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Table;

import lombok.Data;
import net.kiel.dnd.model.Ability.AbilityType;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="character")
@Data
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="player_name")
    private String playerName;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Race race;
    
    @ManyToOne
    @JoinColumn(name="class_id", nullable = false)
    private Class characterClass;
    
    private String background;
  
    private String alignment;
  
    @Column(name = "armor_class")
    private Integer armorClass;
    
    // level
    public Integer getLevel() {
        return proficiency.getLevel();
    }
    
    private Integer xp;
    
    private Integer speed;
    
    @Column(name="hp_max")
    private Integer hpMax;
    
    @Column(name="hp_current")
    private Integer hpCurrent;

    public Integer getInitiative() {
        return getAbility(AbilityType.DEXTERITY).getModifier();
    }
        
    @ManyToOne
    @JoinColumn(name="level", referencedColumnName="level", nullable = false)
    private Proficiency proficiency;
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "character_id")
    @OrderBy("ability_type")
    private Set<Ability> abilities;

    public List<Skill> getSkills() {
        List<Skill> skills = new ArrayList<Skill>();
        
        for (Ability ability : abilities) {
            CollectionUtils.addAll(skills, ability.getSkills().iterator());
        }
        
        Collections.sort(skills, new Comparator<Skill>() {
            @Override
            public int compare(Skill arg1, Skill arg2) {
                return arg1.getSkillType().getName().compareTo(arg2.getSkillType().getName());
            }
        });
        
        return skills;
    }
        
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    @JoinColumn(name = "character_id")
    private Set<CharacterWeapon> weapons;
    
    public Ability getAbility(AbilityType type) {
        for (Ability ability : abilities) {
            if (type.equals(ability.getType())) {
                return ability;
            }
        }
        
        return null;
    }
    
    
    @Column(name="created_datetime")
    private Date createdDateTime;

    @Override
    public String toString() {
        return "Character [id=" + id + ", playerName=" + playerName + ", name=" + name + ", race=" + race
                + ", characterClass=" + characterClass + ", background=" + background + ", alignment=" + alignment
                + ", armorClass=" + armorClass + ", xp=" + xp + ", speed=" + speed + ", hpMax=" + hpMax
                + ", hpCurrent=" + hpCurrent + ", proficiency=" + proficiency + ", createdDateTime=" + createdDateTime
                + "]";
    }
}
