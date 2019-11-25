package com.widehouse.dnd5e.character;

import com.widehouse.dnd5e.dice.Dice;
import com.widehouse.dnd5e.equipment.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.widehouse.dnd5e.character.Level.LV1;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Character implements Creature {
    @NonNull
    private String characterName;
    @NonNull
    private CharacterClass characterClass;
    @NonNull
    private Race race;
    private Alignment alignment;
    private int xp;
    private Level level;
    private int maxHitPoints;
    @NonNull
    private Ability ability;
    private List<Item> equipments;
    private Integer initiative;

    private void init() {
        updateMaxHp();
    }

    public static class CharacterBuilder {
        public Character build() {
            System.out.println("builder");
            Assert.notNull(ability, "Character ability should not null");
            if (level == null) {
                level = LV1;
            }
            if (equipments == null) {
                equipments = new ArrayList<>();
            }

            Character character = new Character(characterName, characterClass, race, alignment, xp, level,
                    maxHitPoints, ability, equipments, initiative);
            character.init();
            return character;
        }
    }

    public void equip(Item item) {
        equipments.add(item);
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
