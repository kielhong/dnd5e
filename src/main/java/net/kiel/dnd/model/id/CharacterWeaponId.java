package net.kiel.dnd.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.Data;
import net.kiel.dnd.model.Character;
import net.kiel.dnd.model.Weapon;

@Data
@Embeddable
@SuppressWarnings("serial")
public class CharacterWeaponId implements Serializable {

    private Integer characterId;
    
    private Weapon weaponId;

}
