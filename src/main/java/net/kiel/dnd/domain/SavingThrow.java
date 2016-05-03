package net.kiel.dnd.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "saving_throw")
@Getter
@Setter
public class SavingThrow {
    @Id
    @GeneratedValue
    Integer id;
    
    @Column(name = "is_proficiency")
    boolean proficiency;
    
    @OneToOne
    Ability ability;

    @Override
    public String toString() {
        return "SavingThrow [id=" + id + ", isProficiency=" + proficiency + "]";
    }    
}
