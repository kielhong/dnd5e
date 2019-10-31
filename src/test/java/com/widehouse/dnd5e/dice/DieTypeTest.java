package com.widehouse.dnd5e.dice;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DieTypeTest {
    @ParameterizedTest
    @CsvSource({"D20,20", "D12,12", "D10,10", "D8,8", "D6,6", "D4,4"})
    void getSide_ThenReturnSideValueOfDie(DieType die, int side) {
        then(die.getSide()).isEqualTo(side);
    }
}