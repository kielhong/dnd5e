package com.widehouse.dnd5e.dice;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Dice {
    DieType dieType;
    Integer diceCount;

    public Dice(DieType dieType, Integer diceCount) {
        this.dieType = dieType;
        this.diceCount = diceCount;
    }

    public static Dice of(DieType dieType, Integer diceCount) {
        return new Dice(dieType, diceCount);
    }

    public List<Integer> roll() {
        return IntStream.range(0, diceCount)
                .mapToObj(i ->  new Random().nextInt(dieType.getSide()) + 1)
                .collect(Collectors.toList());
    }

    public Integer rollSum() {
        return roll().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
