package net.kiel.dnd.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ability_table")
public class AbilityModifier {
    @Id
    private Integer score;
    
    private Integer modifier;
}
