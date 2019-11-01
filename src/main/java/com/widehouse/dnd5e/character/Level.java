package com.widehouse.dnd5e.character;

import java.util.stream.Stream;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum Level {
    LV1(1, 299, 2), LV2(2, 899, 2), LV3(3, 2699, 2), LV4(4, 6499, 2),
    LV5(5, 13999, 3), LV6(6, 22999, 3), LV7(7, 33999, 3), LV8(8, 47999, 3),
    LV9(9, 63999, 4), LV10(10, 84999, 4), LV11(11, 99999, 4), LV12(12, 119999, 4),
    LV13(13, 139999, 5), LV14(14, 164999, 5), LV15(15, 194999, 5), LV16(16, 224999, 5),
    LV17(17, 264999, 6), LV18(18, 304999, 6), LV19(19, 334999, 6), LV20(20, Integer.MAX_VALUE, 6);

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
