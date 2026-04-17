package com.stayverse.model;

public class Hotel extends AbstractProperty {
    public Hotel(String id, String name, String city, double price, double rating, String desc) {
        super(id, name, city, price, rating, desc);
    }
    @Override public String getPropertyType() { return "Premier Hotel"; }
}
