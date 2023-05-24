package com.widehouse.dnd.character.player

enum class LevelTable(val level: Int, val xp: Int) {
    Level1(1, 0),
    Level2(2, 300),
    Level3(3, 900),
    Level4(4, 2700),
    Level5(5, 6500);

    companion object {
        fun of(level: Int) = values().find { it.level == level }
    }
}
