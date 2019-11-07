package com.widehouse.dnd5e.monster;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class MonsterTest {
    @Test
    void buildMonster() {
        Monster goblin = Monster.builder()
                .name("Goblin")
                .armorClass(15)
                .hitPoint(7)
                .xp(50)
                .build();
        // then
        then(goblin)
                .hasFieldOrPropertyWithValue("name", "Goblin")
                .hasFieldOrPropertyWithValue("armorClass", 15)
                .hasFieldOrPropertyWithValue("hitPoint", 7)
                .hasFieldOrPropertyWithValue("xp", 50);
    }
}
