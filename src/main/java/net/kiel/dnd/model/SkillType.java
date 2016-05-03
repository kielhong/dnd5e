package net.kiel.dnd.model;

import lombok.Data;
import net.kiel.dnd.model.Ability.AbilityType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

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
