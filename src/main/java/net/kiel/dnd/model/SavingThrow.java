package net.kiel.dnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "saving_throw")
public class SavingThrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    Integer id;
    
    @Column(name = "is_proficiency")
    @Getter @Setter
    boolean proficiency;
    
    @OneToOne
    @Getter @Setter
    Ability ability;

    
    @Override
    public String toString() {
        return "SavingThrow [id=" + id + ", isProficiency=" + proficiency + "]";
    }    
}
