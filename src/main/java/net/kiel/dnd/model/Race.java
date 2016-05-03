package net.kiel.dnd.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Race {
    public Race(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;
}
