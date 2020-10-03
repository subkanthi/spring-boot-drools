package com.springdrools.model;
import java.util.HashMap;
import java.util.Map.Entry;

public class Purchase {
    //TODO: Implement this model
    private HashMap<Item, Double> items;
    private String stateCreated;
    private double totalDiscount;

    private HashMap<String, Double> taxes = new HashMap<String, Double>()
    {
        {
            put("California", 7.25);
            put("Colorado", 2.90);
            put("India", 18.);
            put("British Columbia", 12.);
        }
    };

    public Purchase(String state) {
        items = new HashMap<>();
        stateCreated = state;
        totalDiscount = 0;
    }

    public void addItem(Item i) {
        items.put(i, 1.0);
    }

    public HashMap<Item, Double> getItems() {
        return items;
    }

    public String getStateCreated() {
        return stateCreated;
    }

    public void setStateCreated(String s) {
        stateCreated = s;
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

    public double getTotalCost() {
        double sum = 0;
        for (Entry<Item, Double> i : items.entrySet()) {
            sum += i.getKey().getCost() * i.getValue();
        }
        return sum * (1 - totalDiscount);
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
        double cost = getTotalCost();
        double tax = cost * 0.01 * taxes.get(stateCreated);
        result += "\nSubtotal Pretax: " + cost;
        result += "\nTax: " + tax;
        result += "\nTotal after Tax: " + (tax + cost);
        return result;
    }
}
