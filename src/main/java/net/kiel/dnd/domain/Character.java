package net.kiel.dnd.domain;

import lombok.Getter;
import lombok.Setter;
import net.kiel.dnd.domain.Ability.AbilityType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
@EntityListeners(AuditingEntityListener.class)
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
    @JoinColumn(name = "class_id", nullable = false)
    private CharacterClass characterClass;

    @Column(nullable = false)
    private Integer level;

    private Integer xp;

    private Integer proficiencyBonus;

    @Column(length = 2000)
    private String background;
  
    private String alignment;

    private Integer armorClass;
    
    private Integer speed;

    private Integer hpMax;

    private Integer hpCurrent;

    private Integer inspiration;

//    public Integer getInitiative() {
//        return getAbility(AbilityType.DEXTERITY).getModifier();
//    }

    @OneToMany
    @OrderBy("ability_type")
    private List<Ability> abilities;

    /**
     * ability
     * @param type AbilityType
     * @return Ability
     */
    public Ability getAbility(AbilityType type) {
        return abilities.stream().filter(a -> a.getType() == type).findFirst().get();
    }

//    /**
//     * skills
//     * @return skill list
//     */
//    public List<Skill> getSkills() {
//        List<Skill> skills = new ArrayList<Skill>();
//
//        for (Ability ability : abilities) {
//            CollectionUtils.addAll(skills, ability.getSkills().iterator());
//        }
//
//        Collections.sort(skills, new Comparator<Skill>() {
//            @Override
//            public int compare(Skill arg1, Skill arg2) {
//                return arg1.getSkillType().getName().compareTo(arg2.getSkillType().getName());
//            }
//        });
//
//        return skills;
//    }

    @ManyToMany(targetEntity = CharacterItem.class)
    private Set<Item> items;

    @ManyToMany(targetEntity =  CharacterWeapon.class)
    private Set<CharacterWeapon> weapons;


    

    @CreatedDate
    private LocalDateTime createDatetime;

    @LastModifiedDate
    private LocalDateTime modifyDatetime;
}
