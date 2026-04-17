package com.stayverse.model;

public class Resort extends AbstractProperty {
    public Resort(String id, String name, String city, double price, double rating, String desc) {
        super(id, name, city, price, rating, desc);
    }
    @Override public String getPropertyType() { return "Signature Resort"; }
}
