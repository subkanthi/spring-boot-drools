package com.springdrools.model;
import java.util.HashMap;
import java.util.Map.Entry;

import java.io.Serializable;

public class Purchase implements Serializable {
    //TODO: Implement this model
    private HashMap<Item, Double> items;
    private String state;
    private double totalDiscount;
    private double tax;

    public Purchase() {
        this("");
    }

    public Purchase(String state) {
        items = new HashMap<>();
        this.state = state;
        totalDiscount = 0;
    }

    public void addItem(Item i) {
        items.put(i, 1.0);
    }

    public HashMap<Item, Double> getItems() {
        return items;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void discountItem(Item i, double amt) {
        items.put(i, amt * items.get(i));
    }

    public void setTotalDiscount(double discount) {
        totalDiscount = discount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTax(double t) {
        tax = t;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalCost() {
        double sum = 0;
        for (Entry<Item, Double> i : items.entrySet()) {
            sum += i.getKey().getCost() * i.getValue();
        }
        return sum;
    }

    @Override
    public String toString() {
        String result = "";
        result += "\nItems:\n";
        for (Entry<Item, Double> e : items.entrySet()) {
            result += e.getKey().toString();
            if (e.getValue() != 1) {
                result += "\nDiscount: " + e.getValue();
            }
            result += "\n";
        }
        double cost = getTotalCost()  * (1 - totalDiscount);
        double t = cost * 0.01 * tax;
        result += "\nSubtotal Pretax: " + cost;
        result += "\nTax: " + t;
        result += "\nTotal after Tax: " + (t + cost);
        return result;
    }
}
