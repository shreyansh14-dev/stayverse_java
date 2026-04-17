package com.stayverse.model;

import java.time.LocalDateTime;

public class Booking {
    private String id;
    private AbstractProperty property;
    private double totalCost;
    private LocalDateTime timestamp;

    public Booking(String id, AbstractProperty property, double totalCost) {
        this.id = id;
        this.property = property;
        this.totalCost = totalCost;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() { return id; }
    public AbstractProperty getProperty() { return property; }
    public double getTotalCost() { return totalCost; }
}
