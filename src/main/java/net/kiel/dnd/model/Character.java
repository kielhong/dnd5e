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
import lombok.Getter;
import lombok.Setter;
import net.kiel.dnd.model.Ability.AbilityType;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Getter
@Setter
public class Character {
    @Id
    @GeneratedValue
    private Long id;

    private String playerName;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Race race;
    
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
    private CharacterClass characterClass;

    @Column(nullable = false)
    private Integer level;

    private String background;
  
    private String alignment;

    private Integer armorClass;

    private Integer xp;
    
    private Integer speed;

    private Integer hpMax;

    private Integer hpCurrent;

    public Integer getInitiative() {
        return getAbility(AbilityType.DEXTERITY).getModifier();
    }
        
//    @ManyToOne
//    @JoinColumn(name="level", referencedColumnName="level")
//    private Proficiency proficiency;
    
    @OneToMany
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
    

    private Date createDatetime;

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", name='" + name + '\'' +
                ", race=" + race +
                ", characterClass=" + characterClass +
                ", background='" + background + '\'' +
                ", alignment='" + alignment + '\'' +
                ", armorClass=" + armorClass +
                ", level=" + level +
                ", xp=" + xp +
                ", speed=" + speed +
                ", hpMax=" + hpMax +
                ", hpCurrent=" + hpCurrent +
                ", abilities=" + abilities +
                ", weapons=" + weapons +
                ", createDatetime=" + createDatetime +
                '}';
    }
}
