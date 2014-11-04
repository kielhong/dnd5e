package net.kiel.dnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import net.kiel.dnd.model.id.CharacterWeaponId;

@Entity
@Table(name = "character_weapon")
@IdClass(CharacterWeaponId.class)
public class CharacterWeapon {
    @Id
    @ManyToOne
    @Getter @Setter
    private Character character;
    
    @Id
    @ManyToOne
    @Getter @Setter
    private Weapon weapon;
    
    @Getter @Setter
    private boolean proficiency;

    
    @Override
    public String toString() {
        return "CharacterWeapon [character.id=" + character.getId() + ", weapon.id=" + weapon.getId() + ", proficiency=" + proficiency + "]";
    }
}
