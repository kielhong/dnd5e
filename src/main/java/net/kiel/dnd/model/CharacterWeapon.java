package net.kiel.dnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import net.kiel.dnd.model.Ability.AbilityType;
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
    
    public Integer getAttackRoll() {
        Ability ability = null;
        if (weapon.getType() == Weapon.WeaponType.SIMPLE_MEELE || weapon.getType() == Weapon.WeaponType.MARTIAL_MEELE ) {
            ability = character.getAbility(AbilityType.STRENGTH);
        } else {
            ability = character.getAbility(AbilityType.DEXTERITY);
        }

        return 0;
//        return proficiency ? ability.getModifier() + character.getProficiency().getBonus() : ability.getModifier();
    }

    
    @Override
    public String toString() {
        return "CharacterWeapon [proficiency=" + proficiency + "]";
    }
}
