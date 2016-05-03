package net.kiel.dnd.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Skill {
    @Id
    @GeneratedValue
    private Integer id;
        
    @ManyToOne
    @JoinColumn(name = "skill_type_id")
    SkillType skillType;
    
    @ManyToOne
    Ability ability;

    private boolean proficiency;

    @Override
    public String toString() {
        return "Skill [id=" + id + ", abilityType=" + ability.getType() + ", proficiency=" + proficiency + "]";
    }
}
