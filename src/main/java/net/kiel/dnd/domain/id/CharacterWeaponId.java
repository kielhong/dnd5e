package net.kiel.dnd.domain.id;

import lombok.Getter;
import lombok.Setter;
import net.kiel.dnd.domain.Character;
import net.kiel.dnd.domain.Weapon;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CharacterWeaponId implements Serializable {
    @Getter @Setter
    private Character character;
    
    @Getter @Setter
    private Weapon weapon;
}
