package net.kiel.dnd.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Weapon {
    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private WeaponType type;
    
    // copper piece
    private Integer cost;
    
    private String damage;

    private String damageType;
    
    public enum WeaponType {
        UNKNOWN, SIMPLE_MEELE, SIMPLE_RANGED, MARTIAL_MEELE, MARTIAL_RANGED;
    }
}
