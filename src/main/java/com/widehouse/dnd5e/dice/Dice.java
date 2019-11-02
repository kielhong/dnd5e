package com.widehouse.dnd5e.dice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Dice {
    public enum With {
        ADVANTAGE, DISADVANTAGE
    }

    private DieType dieType;
    private Integer diceCount;

    public Dice(DieType dieType, Integer diceCount) {
        this.dieType = dieType;
        this.diceCount = diceCount;
    }

    public static Dice of(DieType dieType, Integer diceCount) {
        return new Dice(dieType, diceCount);
    }

    public List<Integer> roll() {
        return IntStream.range(0, diceCount)
                .mapToObj(i -> rollOnce())
                .collect(Collectors.toList());
    }

    public List<Integer> roll(Dice.With with) {
        return IntStream.range(0, diceCount)
                .mapToObj(i -> {
                    List<Integer> list = Arrays.asList(rollOnce(), rollOnce());
                    return with == With.ADVANTAGE ? Collections.max(list) : Collections.min(list);
                })
                .collect(Collectors.toList());
    }

    public Integer rollSum() {
        return roll().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Integer rollOnce() {
        return new Random().nextInt(dieType.getSide()) + 1;
    }
}
