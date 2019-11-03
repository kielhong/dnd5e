package com.widehouse.dnd5e.equipment;

public abstract class Item {
    String name;
    Integer cost;
    Integer weight;

    public Item(String name, Integer cost, Integer weight) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
    }

    /**
     * item name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * item cost based on copper.
     */
    public Integer getCost() {
        return this.cost;
    }

    /**
     * weight of item(unit=lb).
     */
    public Integer getWeight() {
        return this.weight;
    }
}
