package com.springdrools.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Example from Drools Book.
 */
public class Item implements Serializable {

    public enum Category {
        NA, LOW_RANGE, MID_RANGE, HIGH_RANGE,
        SPECIAL_MIDHIGH_RANGE //used in chapter 4
    };
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Double cost;
    private Category category;
    private Double discount = 0.0;

    public Item() {
    }

    public Item(String name, Double cost) {
        this(null, name, cost);
    }

    public Item(Long id, String name, Double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.category = Category.NA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost * (1 - discount);
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.cost);
        hash = 59 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        if (this.category != other.category) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", cost=" + cost
                +  "category=" + category + '}';
    }

}
