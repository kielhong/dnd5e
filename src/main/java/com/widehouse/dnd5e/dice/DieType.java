package com.widehouse.dnd5e.dice;

import lombok.Getter;

@Getter
public enum DieType {
    D4(4), D6(6), D8(8), D10(10), D12(12), D20(20);

    private int side;

    DieType(int side) {
        this.side = side;
    }
}
