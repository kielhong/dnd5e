package net.kiel.dnd.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class AbilityModifier {
    @Id
    private Integer score;
    
    private Integer modifier;
}
