package net.kiel.dnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ability_table")
public class AbilityModifier {
    @Id
    private Integer score;
    
    private Integer modifier;
}
