package net.kiel.dnd.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import net.kiel.dnd.model.id.CharacterWeaponId;

@Data
//@Entity
@Table(name = "character_weapon")
//@IdClass(CharacterWeaponId.class)
public class CharacterWeapon {
//    @Id
//    @AttributeOverrides({
//        @AttributeOverride(name = "characterId", column = @Column(name = "character_id")),
//        @AttributeOverride(name = "weaponId", column = @Column(name = "weapon_id")),
//    })
//    private CharacterWeaponId id;
//    
//    private boolean proficiency;
}
