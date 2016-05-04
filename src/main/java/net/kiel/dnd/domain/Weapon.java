package net.kiel.dnd.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("weapon")
@Getter
@Setter
public class Weapon extends Item {
    @Enumerated(EnumType.ORDINAL)
    private WeaponType weaponType;

    private String damage;

    private String damageType;
    
    public enum WeaponType {
        SIMPLE_MEELE, SIMPLE_RANGED, MARTIAL_MEELE, MARTIAL_RANGED;
    }
}
