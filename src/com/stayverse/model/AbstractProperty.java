package com.stayverse.model;

import java.util.List;

public abstract class AbstractProperty implements Bookable {
    protected String id;
    protected String name;
    protected String city;
    protected double basePrice;
    protected double rating;
    protected String description;
    protected List<String> images;
    protected List<String> amenities;
    protected String roomType = "Premium Suite";
    protected String capacity = "Fits 2 Adults";

    public AbstractProperty(String id, String name, String city, double price, double rating, String desc) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.basePrice = price;
        this.rating = rating;
        this.description = desc;
        this.amenities = List.of("Free Wi-Fi", "Luxury Toiletries", "Mini Bar", "City View");
    }

    @Override public String getID() { return id; }
    @Override public String getName() { return name; }
    @Override public String getCity() { return city; }
    @Override public double getBasePrice() { return basePrice; }
    @Override public double getRating() { return rating; }

    public String getDescription() { return description; }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    
    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> a) { this.amenities = a; }
    
    public String getRoomType() { return roomType; }
    public String getCapacity() { return capacity; }

    public abstract String getPropertyType();
}
