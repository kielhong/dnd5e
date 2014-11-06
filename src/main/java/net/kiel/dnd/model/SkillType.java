package net.kiel.dnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import net.kiel.dnd.model.Ability.AbilityType;
import lombok.Data;

@Data
@Entity
@Table(name = "skill_type")
public class SkillType {
    @Id
    Integer id;
    
    String name;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ability_type")
    AbilityType abilityType;
}
