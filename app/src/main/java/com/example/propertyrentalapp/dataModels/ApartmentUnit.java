package com.example.propertyrentalapp.dataModels;

import android.util.Log;

import java.io.Serializable;

/**
 * Class for Apartment or Unit Rental Property data
 * 16/9/2020 - by Alan K based on RPNode by Ben C on 10/9/2020
 * see also data description on the wiki page
 * https://gitlab.cecs.anu.edu.au/u6481257/comp2100-software-project/-/wikis/Documentation:-Rental-Property-Data-Types-and-Tree-Structure
 */

public class ApartmentUnit implements Serializable {
    private String name; // Name of this property if it is unique, or else AirBnB
    private int price; // Whole dollars price to rent per night for this rental property, eg $30, $200, $1200
    private int maxOccupancy; // Maximum number of people who can stay at this property eg, 1, 2, 4
    private int nights; // Maximum number of nights this property is available from tonight before another booking eg 1, 3, 7
    private int qualityScore;
    private int locationScore;
    private double lat;  // Latitude location of rental property eg Canberra latitude is -35.282001
    private double lon;  // Longitude location of rental property eg Canberra longitude is 149.128998

    public ApartmentUnit(){
    }

    public ApartmentUnit (String name, int price, int maxOccupancy, int nights, int qualityScore, int locationScore, double lat, double lon) {
        this.name = name;
        this.price = price;
        this.maxOccupancy = maxOccupancy;
        this.nights = nights;
        this.qualityScore = qualityScore;
        this.locationScore = locationScore;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString(){
        return "Price: " + this.price + ", " + "Distance: " + Math.round(this.getDistance()) + "m, " +
                "Occupancy: " + this.maxOccupancy + ", " + "Quality: " + this.qualityScore + ", " +
                "Name: " + this.name + ", "+ "Max Nights: " + this.nights + ", " +
                "Location Score: " + this.locationScore + ", "  +
                "Latitude: " + this.lat + ", " + "Longtitude: " + this.lon;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public int getQualityScore(){
        return this.qualityScore;
    }

    public int getLocationScore(){
        return this.locationScore;
    }

    public int getMaxOccupancy(){
        return this.maxOccupancy;
    }

    public int getNights(){
        return this.nights;
    }

    public double getLat(){
        return this.lat;
    }

    public double getLon(){
        return this.lon;
    }


    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setMaxOccupancy(int maxOccupancy){
        this.maxOccupancy = maxOccupancy;
    }

    public void setLocationScore(int locationScore){
        this.locationScore = locationScore;
    }

    public void setQualityScore(int qualityScore){
        this.qualityScore = qualityScore;
    }

    public void setNights(int nights){
        this.nights = nights;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public void setLon(double lon){
        this.lon = lon;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof ApartmentUnit && ((ApartmentUnit) object).getName() == this.name && ((ApartmentUnit) object).getPrice() == this.price
        && ((ApartmentUnit) object).getMaxOccupancy() == this.maxOccupancy && ((ApartmentUnit) object).getNights() == this.nights
        && ((ApartmentUnit) object).getLocationScore() == this.locationScore && ((ApartmentUnit) object).getLat() == this.lat
        && ((ApartmentUnit) object).getLon() == this.lon && ((ApartmentUnit) object).getQualityScore() == this.qualityScore){
            return true;
        } else {
            return false;
        }
    }

    public double getDistance (double lat1, double lon1) {
        // Calculate the distance from specified (lat1,long1) coordinates to this Apartment (lat,long)
        final double R = 6371000; // metres
        double x = (this.lon-lon1) * Math.PI/180 * Math.cos((lon1+this.lon)/2*Math.PI/180);
        double y = (this.lat-lat1) * Math.PI/180;

        double d = Math.sqrt(x*x + y*y) * R;
        //Log.d("Distance ",Double.toString(d));
        return d; // distance in metres between two coordinates
    }

    public double getDistance () {
        // If no location given then calculate the distance from ANU Campus coordinates
        // to this Apartment (lat,long)
        return this.getDistance(-35.2777,149.1185);
    }

    public boolean queryMatch (SearchQuery sq) {
        // Return true if this ApartmentUnit matches the SearchQuery condition sq
        return ( (this.price < sq.getMaxPrice()) && (this.price > sq.getMinPrice())
                &&(this.getDistance() < sq.getMaxDistance()) && (this.getDistance() > sq.getMinDistance())
                &&(this.maxOccupancy < sq.getMaxPeople()) && (this.maxOccupancy > sq.getMinPeople())
                &&(this.nights < sq.getMaxNights()) && (this.nights > sq.getMinNights())
                &&(this.qualityScore < sq.getMaxQuality()) && (this.qualityScore > sq.getMinQuality()));
    }



}
