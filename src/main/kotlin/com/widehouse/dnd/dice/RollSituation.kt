package com.widehouse.dnd.dice

class RollSituation(
    val dice: List<Dice>,
    val condition: RollCondition
) {
    companion object {
        fun of(dice: Dice, condition: RollCondition = RollCondition.NORMAL) =
            RollSituation(listOf(dice), condition)

        fun of(dice: List<Dice>, condition: RollCondition = RollCondition.NORMAL) =
            RollSituation(dice, condition)

        fun of(vararg dice: Dice, condition: RollCondition) =
            RollSituation(dice.toList(), condition)
    }
}
