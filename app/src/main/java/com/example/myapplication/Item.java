package com.example.myapplication;

public class Item {
    private String name;
    private String description;
    private String details;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }
}
