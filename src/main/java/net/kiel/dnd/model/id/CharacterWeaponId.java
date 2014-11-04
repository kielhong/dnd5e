package net.kiel.dnd.model.id;

import java.io.Serializable;

import lombok.Data;
import net.kiel.dnd.model.Weapon;

@Data
@SuppressWarnings("serial")
public class CharacterWeaponId implements Serializable {
    private Character character;
    
    private Weapon weapon;
}
