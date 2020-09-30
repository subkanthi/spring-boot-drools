package com.springdrools.model;
import java.util.ArrayList;

public class Purchase {
    //TODO: Implement this model
    private ArrayList<Item> itemsPurchased;
    private State stateCreated;
    private double totalDiscount;

    public Purchase(State s) {
        itemsPurchased = new ArrayList<Item>();
        stateCreated = s;
        totalDiscount = 0;
    }

    public void addItem(Item i) {
        itemsPurchased.add(i);
    }

    public ArrayList<Item> getItemsPurchased() {
        return itemsPurchased;
    }

    public State getStateCreated() {
        return stateCreated;
    }

    public void discountItem(Item discounted, double amount) {
        for (Item i : itemsPurchased) {
            if (i.equals(discounted)) {
                i.setCost(i.getCost() * amount);
                return;
            }
        }
    }

    public void setTotalDiscount(double discount) {
        totalDiscount = discount;
    }

    public double getTotalCost() {
        double sum = 0;
        for (Item i : itemsPurchased) {
            sum += i.getCost();
        }
        return sum * (1 - totalDiscount) * (1 + stateCreated.getTax());
    }

    @Override
    public String toString() {

    }
}
