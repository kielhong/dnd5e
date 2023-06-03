package com.widehouse.dnd.dice

class RollSituation(
    val dice: List<Dice>,
    val condition: RollCondition
) {
    companion object {
        fun of(dice: Dice) =
            RollSituation(listOf(dice), RollCondition.NORMAL)

        fun of(dice: List<Dice>, condition: RollCondition = RollCondition.NORMAL) =
            RollSituation(dice, condition)

        fun of(vararg dice: Dice, condition: RollCondition) =
            RollSituation(dice.toList(), condition)
    }
}
