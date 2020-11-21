package com.example.propertyrentalapp.dataModels;

public class Node {

    private ApartmentUnit apartmentUnit;
    private int key;  // Generic key up to 9 digits can be constructed from price, lat, long, distance or a combination

    public Node left, right;

    public Node (ApartmentUnit apartmentUnit) {
        this.apartmentUnit = apartmentUnit;
        this.key = priceKey(apartmentUnit);

        this.left = null;
        this.right = null;
    }

    public Node (ApartmentUnit apartmentUnit, int key) {
        this.apartmentUnit = apartmentUnit;
        this.key = key;

        this.left = null;
        this.right = null;
    }

    public int priceKey (ApartmentUnit au) {
        // Given an ApartmentUnit data instance, calculate the key for the Tree that is sorted by price

        // first 4 digits made up of the price in dollars
        int price4 = au.getPrice();

        // second 5 digits made up of distance relative to ANU (or app user location)
        int dist5 = (int) Math.round((au.getDistance()));

        return price4 * 100000 + dist5; // assemble 9 digit key
    }

    public int distKey (ApartmentUnit au) {
        // Given an ApartmentUnit data instance, calculate the key for the Tree that is sorted by distance

        // first 5 digits made up of distance relative to ANU (or app user location)
        int dist5 = (int) Math.round((au.getDistance()));

        // second 4 digits made up of the price in dollars
        int price4 = au.getPrice();

        return dist5 * 10000 + price4; // assemble 9 digit key
    }

    public void incrementKey () {
        // in the rare case of finding this node has a duplicate key of another node in the tree
        // increment it by one.  this should only be used when placing a node
        this.key += 1;
    }

    public int getKey () { return this.key; }

    public ApartmentUnit getApartmentUnit () { return this.apartmentUnit; }





}
