package net.kiel.dnd.domain;

import lombok.Data;
import net.kiel.dnd.domain.id.CharacterItemId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 * Created by kiel on 2016. 5. 4..
 */
@Entity
@IdClass(CharacterItemId.class)
@Data
public class CharacterItem {
    @Id
    @ManyToOne
    private Character character;

    @Id
    @ManyToOne
    private Item item;

    private Integer count = 0;

}
