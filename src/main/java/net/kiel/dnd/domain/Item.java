package net.kiel.dnd.domain;

import lombok.Data;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by kiel on 2016. 5. 4..
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "itemType")
@Data
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer cost;

    private Integer weight;

    private String itemType;
}
