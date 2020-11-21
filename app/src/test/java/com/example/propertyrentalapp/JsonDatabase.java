package com.example.propertyrentalapp;

import com.example.propertyrentalapp.dataModels.ApartmentUnit;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JsonDatabase {
    public List<ApartmentUnit> apartments;

    public JsonDatabase(){
        apartments = new ArrayList<>();
    }

    public JsonDatabase(List<ApartmentUnit> apartments){
        this.apartments = apartments;
    }


    @Override
    public String toString() {
        return "Database of " + apartments.size() + " apartments. They are " + apartments;
    }

    void saveToJson(String fileName){
        File file = new File(fileName);
        FileWriter out;
        try{
            out = new FileWriter(file);
            JSONArray root = saveUnit(apartments);
            root.writeJSONString(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    JSONArray saveUnit(List<ApartmentUnit> apartments){
        JSONArray root = new JSONArray();
        for (ApartmentUnit apartmentUnit:apartments) {
            JSONObject apartmentObject = new JSONObject();
            apartmentObject.put("name",apartmentUnit.getName());
            apartmentObject.put("price",apartmentUnit.getPrice());
            apartmentObject.put("maxOccupancy",apartmentUnit.getMaxOccupancy());
            apartmentObject.put("nights",apartmentUnit.getNights());
            apartmentObject.put("qualityScore",apartmentUnit.getQualityScore());
            apartmentObject.put("locationScore",apartmentUnit.getLocationScore());
            apartmentObject.put("lat",apartmentUnit.getLat());
            apartmentObject.put("lon",apartmentUnit.getLon());
            root.add(apartmentObject);
        }
        return root;
    }


    static JsonDatabase loadFromJson(String fileName){
        JsonDatabase database = new JsonDatabase();
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(inputStreamReader);
            database.apartments = loadApartment(array);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return database;
    }

    static List<ApartmentUnit> loadApartment(JSONArray array) {
        List<ApartmentUnit> root = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = (JSONObject) array.get(i);
            ApartmentUnit apartment;
            String name = (String) obj.get("name");
            int price = ((Long) obj.get("price")).intValue();
            int maxOccupancy = ((Long) obj.get("maxOccupancy")).intValue();
            int nights = ((Long) obj.get("nights")).intValue();
            int qualityScore = ((Long) obj.get("qualityScore")).intValue();
            int locationScore = ((Long) obj.get("locationScore")).intValue();
            double lat = (double) obj.get("lat");
            double lon = (double) obj.get("lon");
            apartment = new ApartmentUnit(name,price, maxOccupancy, nights, qualityScore, locationScore, lat, lon);
            root.add(apartment);
        }
        return root;
    }
}
