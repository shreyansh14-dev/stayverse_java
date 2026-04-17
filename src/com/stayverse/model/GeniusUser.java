package com.stayverse.model;

public class GeniusUser extends User {
    private double discountRate = 0.15;
    public GeniusUser(String email, String name) {
        super(email, name, 5);
    }
    public double getDiscountRate() { return discountRate; }
    @Override public String getName() { return "⭐ " + super.getName(); }
}
