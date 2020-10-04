package com.springdrools.model;
import java.util.HashMap;

public class State {

    private final String name;
    private final HashMap<String, Double> tax = new HashMap<String, Double>()
    {
        {
            put("California", 7.25);
            put("Colorado", 2.90);
            put("India", 18.);
            put("British Columbia", 12.);
        }
    };

    public State(String name) {
        this.name = name;
    }

    public double getTax() {
        if (tax.containsKey(name)) {
            return tax.get(name);
        }
        return 0;
    }
}
