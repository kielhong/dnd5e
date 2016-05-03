package net.kiel.dnd.model;

import lombok.Getter;
import lombok.Setter;
import net.kiel.dnd.model.Ability.AbilityType;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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

    @Column(length = 2000)
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

    private Integer strength;

    private Integer dexterity;

    private Integer constitution;

    private Integer intelligence;

    private Integer wisdom;

    private Integer charisma;

    //    @ManyToOne
    //    @JoinColumn(name="level", referencedColumnName="level")
    //    private Proficiency proficiency;
    
    @OneToMany
    @JoinColumn(name = "character_id")
    @OrderBy("ability_type")
    private Set<Ability> abilities;

    /**
     * skills
     * @return skill list
     */
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

    /**
     * ability
     * @param type AbilityType
     * @return Ability
     */
    public Ability getAbility(AbilityType type) {
        for (Ability ability : abilities) {
            if (type.equals(ability.getType())) {
                return ability;
            }
        }
        
        return null;
    }
    

    private Date createDatetime;
}
