package net.kiel.dnd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by kiel on 2016. 5. 4..
 */
@Entity
@Data
@AllArgsConstructor
public class Level {
    @Id
    private Integer level;

    private Integer xp;

    private Integer proficiencyBonus;
}