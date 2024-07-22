package com.example.travel_app_srilanka.Activities;

public class Booking {
    private String name;
    private String location;
    private String description;
    private String price;

    public Booking(String name, String location, String description, String price) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
