package com.springdrools.model;
import java.util.HashMap;

public class State {

    private String name;
    private HashMap<String, Double> tax = new HashMap<String, Double>()
    {
        {
            put("California", 7.25);
            put("Colorado", 2.90);
            put("India", 18.);
            put("British Columbia", 12.);
        }
    };

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
