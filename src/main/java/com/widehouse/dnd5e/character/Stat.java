package com.widehouse.dnd5e.character;

import lombok.Getter;

@Getter
public class Stat {
    private Integer score;
    private Integer modifier;

    public Stat(Integer score) {
        this.score = score;
        setModifier();
    }

    public static Stat of(Integer score) {
        return new Stat(score);
    }

    private void setModifier() {
        this.modifier = this.score / 2 - 5;
    }
}
