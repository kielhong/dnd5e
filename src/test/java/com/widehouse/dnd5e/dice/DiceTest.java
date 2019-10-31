package com.widehouse.dnd5e.dice;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class DiceTest {
    @ParameterizedTest
    @EnumSource(DieType.class)
    void rollDice_ThenReturnValueList(DieType dieType) {
        // given
        int count = new Random().nextInt(20);
        Dice dice = new Dice(dieType, count);
        // when
        List<Integer> results = dice.roll();
        // then
        then(results).hasSize(count)
                .allMatch(i -> i > 0 && i <= dieType.getSide());
    }

    @ParameterizedTest
    @EnumSource(DieType.class)
    void rollSum_ThenSumRollResult(DieType dieType) {
        // given
        int count = new Random().nextInt(5);
        Dice dice = new Dice(dieType, count);
        // when
        Integer result = dice.rollSum();
        // then
        then(result).isBetween(1 * count, dieType.getSide() * count);
    }
}
