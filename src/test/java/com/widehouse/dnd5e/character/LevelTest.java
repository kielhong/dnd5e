package com.widehouse.dnd5e.character;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LevelTest {
    @ParameterizedTest
    @CsvSource({"1,LV1", "2,LV2", "4,LV4", "8,LV8", "16,LV16", "20,LV20"})
    void of_GivenLevelValue_ThenGetLevel(int value, Level lv) {
        then(Level.of(value)).isEqualTo(lv);
    }
}