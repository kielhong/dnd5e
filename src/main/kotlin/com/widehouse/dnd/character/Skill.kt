package com.widehouse.dnd.character

import com.widehouse.dnd.character.AbilityType.Charisma
import com.widehouse.dnd.character.AbilityType.Dexterity
import com.widehouse.dnd.character.AbilityType.Intelligence
import com.widehouse.dnd.character.AbilityType.Strength
import com.widehouse.dnd.character.AbilityType.Wisdom

sealed class Skill(val abilityType: AbilityType)

object Athletics : Skill(Strength)
object Acrobatics : Skill(Dexterity)
object SleightOfHand : Skill(Dexterity)
object Stealth : Skill(Dexterity)
object Arcana : Skill(Intelligence)
object History : Skill(Intelligence)
object Investigation : Skill(Intelligence)
object Nature : Skill(Intelligence)
object Religion : Skill(Intelligence)
object AnimalHandling : Skill(Wisdom)
object Insight : Skill(Wisdom)
object Medicine : Skill(Wisdom)
object Perception : Skill(Wisdom)
object Survival : Skill(Wisdom)
object Deception : Skill(Charisma)
object Intimidation : Skill(Charisma)
object Performance : Skill(Charisma)
object Persuasion : Skill(Charisma)
