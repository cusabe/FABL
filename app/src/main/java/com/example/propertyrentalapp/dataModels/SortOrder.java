package com.example.propertyrentalapp.dataModels;

public enum SortOrder {
    LOWEST("Sort by Lowest "),
    HIGHEST("Sort by Highest ");
    String order;

    SortOrder(String order) {
        this.order = order;
    }

    public String display() {
        return this.order;
    }

}
