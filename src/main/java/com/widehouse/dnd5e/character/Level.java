package com.widehouse.dnd5e.character;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum Level {
    L1(1, 0, 2), L2(2, 300, 2), L3(3, 900, 2), L4(4, 2700, 2),
    L5(5, 6500, 3), L6(6, 14000, 3), L7(7, 23000, 3), L8(8, 34000, 3),
    L9(9, 48000, 4), L10(10, 64000, 4), L11(11, 85000, 4), L12(12, 100000, 4),
    L13(13, 120000, 5), L14(14, 140000, 5), L15(15, 165000, 5), L16(16, 195000, 5),
    L17(17, 225000, 6), L18(18, 265000, 6), L19(19, 305000, 6), L20(20, 335000, 6);

    private int level;
    private int xp;
    private int proficiency;

    Level(int level, int xp, int proficiency) {
        this.level = level;
        this.xp = xp;
        this.proficiency = proficiency;
    }

    public static Level of(int level) {
        return Stream.of(values())
                .filter(lv -> lv.level == level)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
