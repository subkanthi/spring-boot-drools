package com.springdrools.model;
import java.util.ArrayList;

import java.io.Serializable;
import java.util.Arrays;

public class Purchase implements Serializable {

    private ArrayList<Item> items;
    private State state;
    private double totalCost;
    private double totalDiscount;

    public Purchase(State state, Item[] items) {
        this.items = new ArrayList<>();
        this.items.addAll(Arrays.asList(items));
        this.state = state;
        totalCost = sumItemCosts();
        totalDiscount = 0;
    }

    public void setItem(int index, Item i) {
        items.set(index, i);
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

    public Result packageResult() {
        ArrayList<String> discounts = new ArrayList<>();
        for (Item i : items) {
            if (i.getDiscount() != 0) {
                discounts.add("Discount on " + i.getName() + ": " + 100 * i.getDiscount() + "%");
            }
        }
        double cost = sumItemCosts() * (1 - 0.01 * totalDiscount);
        double t = cost * 0.01 * state.getTax();
        return new Result(items, discounts, round(cost), round(t), round(t + cost));
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

    public class Result {
        private ArrayList<Item> items;
        private ArrayList<String> discounts;
        private Double subtotalPretax;
        private Double tax;
        private Double totalAfterTax;

        public Result(ArrayList<Item> items, ArrayList<String> discounts, Double subtotalPretax,
                      Double tax, Double totalAfterTax) {
            this.items = items;
            this.discounts = discounts;
            this.subtotalPretax = subtotalPretax;
            this.tax = tax;
            this.totalAfterTax = totalAfterTax;
        }

        public void setItems(ArrayList<Item> items) {
            this.items = items;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public void setDiscounts(ArrayList<String> discounts) {
            this.discounts = discounts;
        }

        public ArrayList<String> getDiscounts() {
            return discounts;
        }

        public void setSubtotalPretax(Double subtotalPretax) {
            this.subtotalPretax = subtotalPretax;
        }

        public Double getSubtotalPretax() {
            return subtotalPretax;
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public Double getTax() {
            return tax;
        }

        public void setTotalAfterTax(Double totalAfterTax) {
            this.totalAfterTax = totalAfterTax;
        }

        public Double getTotalAfterTax() {
            return totalAfterTax;
        }
    }
}
