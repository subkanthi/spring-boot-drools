package com.springdrools.model;
import java.util.Map;

public class State {

    private String name;
    private Map<String, Double> tax = Map.of(
            "California", 7.25,
            "Colorado", 2.90,
            "India", 18.,
            "British Columbia", 12.
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
