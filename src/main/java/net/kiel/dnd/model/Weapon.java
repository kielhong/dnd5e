package net.kiel.dnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "weapon")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private WeaponType type;
    
    // copper piece
    private Integer cost;
    
    private String damage;
    
    @Column(name = "damage_type")
    private String damageType;
    
    public enum WeaponType {
        UNKNOWN, SIMPLE_MEELE, SIMPLE_RANGED, MARTIAL_MEELE, MARTIAL_RANGED;
    }
}
