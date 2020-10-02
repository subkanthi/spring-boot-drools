package com.springdrools.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Purchase {
    //TODO: Implement this model
    private ArrayList<Item> items;
    private HashMap<Item, Double> discounts;
    private State stateCreated;
    private double totalDiscount;

    public Purchase(State s) {
        items = new ArrayList<>();
        discounts = new HashMap<>();
        stateCreated = s;
        totalDiscount = 0;
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public State getStateCreated() {
        return stateCreated;
    }

    public void setStateCreated(State s) {
        stateCreated = s;
    }

    public void discountItem(Item i, double amt) {
        discounts.put(i, amt * (discounts.containsKey(i) ? discounts.get(i) : 1));
    }

    public HashMap<Item, Double> getDiscounts() {
        return discounts;
    }

    public void setTotalDiscount(double discount) {
        totalDiscount = discount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getTotalCost() {
        double sum = 0;
        for (Item i : items) {
            sum += i.getCost() * (discounts.containsKey(i) ? discounts.get(i) : 1);
        }
        return sum * (1 - totalDiscount);
    }

    @Override
    public String toString() {
        String result = "Discounts:\n";
        for (Entry<Item, Double> e : discounts.entrySet()) {
            result += (e.getKey().getName() + ": " + e.getValue() + "\n");
        }
        result += "\nItems:\n";
        for (Entry<Item, Double> e : discounts.entrySet()) {
            result += e.getKey().toString() + "\n";
        }
        double cost = getTotalCost();
        double tax = cost * stateCreated.getTax();
        result += "\nSubtotal Pretax: " + cost;
        result += "\nTax: " + tax;
        result += "\nTotal after Tax: " + (tax + cost);
        return result;
    }
}
