package com.springdrools.model;
import java.util.ArrayList;

import java.io.Serializable;

public class Purchase implements Serializable {

    private ArrayList<Item> items;
    private State state;
    private double totalDiscount;

    public Purchase(State state, Item[] items) {
        this.items = new ArrayList<>();
        for (Item i : items) {
            addItem(i);
        }
        this.state = state;
        totalDiscount = 0;
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setTotalDiscount(double discount) {
        totalDiscount = discount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    private double sumItemCosts() {
        double sum = 0;
        for (Item i : items) {
            sum += i.getCost();
        }
        return sum;
    }

    public double getTotalCost() {
        return round(sumItemCosts() * (1 - 0.01 * totalDiscount) * (1 + 0.01 * state.getTax()));
    }

    @Override
    public String toString() {
        String result = "";
        result += "\nItems:\n";
        for (Item i : items) {
            result += i.toString() + "\n";
            if (i.getDiscount() != 0) {
                result += "Discount: " + 100 * i.getDiscount() + "%\n";
            }
        }
        double cost = sumItemCosts() * (1 - 0.01 * totalDiscount);
        double t = cost * 0.01 * state.getTax();
        result += "\nSubtotal Pretax: " + round(cost);
        result += "\nTax: " + round(t);
        result += "\nTotal after Tax: " + round(t + cost);
        return result;
    }

    private static double round(double d) {
        return Math.round(d * 100) / 100.;
    }
}
