package com.widehouse.dnd5e.character;

import static com.widehouse.dnd5e.character.Level.LV1;

import com.widehouse.dnd5e.dice.Dice;
import com.widehouse.dnd5e.equipment.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(buildMethodName = "create")
public class Character implements Creature {
    private String characterName;
    private CharacterClass characterClass;
    private Race race;
    private Alignment alignment;
    @Builder.Default
    private Integer xp = 0;
    @Builder.Default
    private Level level = LV1;
    @Builder.Default
    private Integer maxHitPoints = 0;
    private Ability ability;
    @Builder.Default
    private List<Item> equipments = new ArrayList<>();
    private Integer initiative;

    public void init() {
        updateMaxHp();
    }

    public void equip(Item item) {
        equipments.add(item);
    }

    public static class CharacterBuilder {
        public Optional<Character> buildOptional() {
            return Optional.of(this.build());
        }

        public Character build() {
            Character character = this.create();
            character.init();
            return character;
        }
    }

    public void setAbilities(int str, int dex, int con, int intel, int wis, int cha) {
        this.ability = new Ability(str, dex, con, intel, wis, cha);

        init();
    }

    public void earnXp(int xp) {
        this.xp += xp;

        advanceLevel();
    }

    public Integer getMaxHitPoints() {
        return this.maxHitPoints;
    }

    public Integer getProficiency() {
        return this.level.getProficiency();
    }

    @Override
    public String getName() {
        return this.characterName;
    }

    @Override
    public Integer getInitiative() {
        return this.initiative;
    }

    @Override
    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    private void advanceLevel() {
        if (this.xp <= this.level.getXp()) {
            return;
        }

        this.level = Level.of(this.level.getLevel() + 1);
        this.updateMaxHp();

        advanceLevel();
    }

    private void updateMaxHp() {
        int conModifier = 0;
        if (this.ability != null && this.ability.constitution != null) {
            conModifier = this.ability.constitution.getModifier();
        }
        this.maxHitPoints += getNextMaxHp(this.level) + conModifier;
    }

    private Integer getNextMaxHp(Level lv) {
        return (lv == LV1)
                ? characterClass.getHitDie().getSide() : Dice.of(1, characterClass.getHitDie()).rollSum();
    }
}
