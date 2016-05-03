package net.kiel.dnd.model.id;

import lombok.Getter;
import lombok.Setter;
import net.kiel.dnd.model.Character;
import net.kiel.dnd.model.Weapon;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CharacterWeaponId implements Serializable {
    @Getter @Setter
    private Character character;
    
    @Getter @Setter
    private Weapon weapon;
}
