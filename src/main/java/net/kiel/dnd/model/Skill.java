package net.kiel.dnd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
        
    @ManyToOne
    @JoinColumn(name = "skill_type_id")
    @Getter @Setter
    SkillType skillType;
    
    @ManyToOne
    @Getter @Setter
    Ability ability;
    
    @Getter @Setter
    private boolean proficiency;

    @Override
    public String toString() {
        return "Skill [id=" + id + ", abilityType=" + ability.getType() + ", proficiency=" + proficiency + "]";
    }
}
