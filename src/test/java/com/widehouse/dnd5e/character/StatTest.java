package com.widehouse.dnd5e.character;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.BDDAssertions.then;

class StatTest {
    @ParameterizedTest
    @CsvSource({"1,-5", "2,-4", "3,-4", "4,-3", "11, 0", "14,2", "18,4", "30, 10"})
    void testModifier(Integer score, Integer modifier) {
        Stat stat = Stat.of(score);

        then(stat.getModifier()).isEqualTo(modifier);
    }
}