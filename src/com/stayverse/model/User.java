package com.stayverse.model;

public class User {
    protected String email;
    protected String name;
    protected int loyaltyLevel;

    public User(String email, String name, int loyaltyLevel) {
        this.email = email;
        this.name = name;
        this.loyaltyLevel = loyaltyLevel;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public int getLoyaltyLevel() { return loyaltyLevel; }
}
