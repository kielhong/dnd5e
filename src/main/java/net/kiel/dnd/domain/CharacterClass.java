package net.kiel.dnd.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
