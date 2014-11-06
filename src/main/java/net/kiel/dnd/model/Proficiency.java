package net.kiel.dnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Proficiency {
    @Id
    private Integer level;

    private Integer bonus;
}