package net.kiel.dnd.domain;

import lombok.Getter;
import lombok.Setter;
import net.kiel.dnd.domain.Ability.AbilityType;
import net.kiel.dnd.domain.id.CharacterWeaponId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "character_weapon")
@IdClass(CharacterWeaponId.class)
@Getter
@Setter
public class CharacterWeapon {
    @Id
    @ManyToOne
    private Character character;
    
    @Id
    @ManyToOne
    private Weapon weapon;

    private boolean proficiency;

    /**
     * Calculate Attack Roll
     * @return attack roll
     */
    public Integer getAttackRoll() {
        Ability ability = null;
        if (weapon.getWeaponType() == Weapon.WeaponType.SIMPLE_MEELE
                || weapon.getWeaponType() == Weapon.WeaponType.MARTIAL_MEELE ) {
            ability = character.getAbility(AbilityType.STRENGTH);
        } else {
            ability = character.getAbility(AbilityType.DEXTERITY);
        }

        return 0;
        // return proficiency ? ability.getModifier() + character.getProficiency().getBonus() : ability.getModifier();
    }

    
    @Override
    public String toString() {
        return "CharacterWeapon [proficiency=" + proficiency + "]";
    }
}
