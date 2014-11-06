package net.kiel.dnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "race")
public class Race {
    @Id
    private Integer id;
    
    private String name;
}
