package net.kiel.dnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "class")
public class Class {
    @Id
    private Integer id;
    
    private String name;
}
