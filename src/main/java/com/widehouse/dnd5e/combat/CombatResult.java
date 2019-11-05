package com.widehouse.dnd5e.combat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class CombatResult {
    private Boolean hit;
    private Integer damage;
}
