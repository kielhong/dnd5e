package com.widehouse.dnd5e.combat;

import com.widehouse.dnd5e.character.Character;
import com.widehouse.dnd5e.character.Creature;
import com.widehouse.dnd5e.dice.Dice;
import com.widehouse.dnd5e.monster.Monster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CombatService {
    private Dice dice;
    private List<Character> characters;
    private List<Monster> monsters;

    private List<Creature> creatures = new ArrayList<>();

    public CombatService(Dice dice, List<Character> characters, List<Monster> monsters) {
        this.dice = dice;
        this.characters = characters;
        this.monsters = monsters;
    }

    public void rollInitiative() {
        creatures.addAll(characters);
        creatures.addAll(monsters);
        creatures.forEach(c -> c.setInitiative(dice.rollSum() + c.getAbility().getDexterity().getModifier()));

        creatures.sort(Comparator.comparingInt(Creature::getInitiative).reversed());
    }

    public List<Creature> getCreatures() {
        return this.creatures;
    }
}
