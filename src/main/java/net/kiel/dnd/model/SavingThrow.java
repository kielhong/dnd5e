package net.kiel.dnd.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "saving_throw")
public class SavingThrow {
    @Id
    private Integer id;
    
    private Boolean proficiency;
        
    @OneToOne
    private Ability ability;
    
//    public Integer getValue() {        
//        return getAbility().getModifier() + (proficiency ? getAbility().getCharacter().getProficiency().getBonus() : 0); 
//    }
}
