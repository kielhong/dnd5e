package net.kiel.dnd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CharacterClass {
    public CharacterClass(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;
}
