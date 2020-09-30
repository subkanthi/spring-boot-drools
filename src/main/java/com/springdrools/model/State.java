package com.springdrools.model;
import java.util.Map;
import java.util.Map.entry;

public class State {

    private String name;
    private Map<String, Double> tax = Map.ofEntries(
            entry("California", 7.25),
            entry("Colorado", 2.90),
            entry("India", 18.),
            entry("British Columbia", 12.)
    );

    public State() {
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public double getTax() {
        return 0.01 * tax.get(name);
    }
}
