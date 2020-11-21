package com.example.propertyrentalapp.dataModels;

public enum SortVariable {
    PRICE("Price"),
    DISTANCE("Distance");
    String order;

    SortVariable(String order) {
        this.order = order;
    }

    public String display() {
        return this.order;
    }

}

