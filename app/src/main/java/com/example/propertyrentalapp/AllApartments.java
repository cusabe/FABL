package com.example.propertyrentalapp;
import android.content.Context;

import com.example.propertyrentalapp.dataModels.ApartmentUnit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class AllApartments {

    ArrayList<ApartmentUnit> allApartments;


    public AllApartments(ArrayList<ApartmentUnit> listOfApartments){
        this.allApartments = listOfApartments;
    }

    public AllApartments() {
        this.allApartments = new ArrayList<>();
    }

    public ArrayList<ApartmentUnit> getListOfAllApartments(){
        return allApartments;
    }

    public JSONObject transfer(ApartmentUnit apartmentUnit){
        JSONObject obj = new JSONObject();
        obj.put("name",apartmentUnit.getName());
        obj.put("price",apartmentUnit.getPrice());
        obj.put("maxOccupancy",apartmentUnit.getMaxOccupancy());
        obj.put("nights",apartmentUnit.getNights());
        obj.put("qualityScore",apartmentUnit.getQualityScore());
        obj.put("locationScore",apartmentUnit.getLocationScore());
        obj.put("lat",apartmentUnit.getLat());
        obj.put("lon",apartmentUnit.getLon());
        return obj;
    }

    public void save(Context context, String fileName, ArrayList<ApartmentUnit> apartmentsToSave){
        JSONArray jsonArray = new JSONArray();
        for(ApartmentUnit itemToConvert: apartmentsToSave){
            jsonArray.add(transfer(itemToConvert));
        }
        try{
            File file = new File(context.getFilesDir(),fileName);

            FileWriter fileWriter = new FileWriter(file);
            jsonArray.writeJSONString(fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ApartmentUnit reTransfer(JSONObject obj){
        String name = (String) obj.get("name");
        int price = ((Long) obj.get("price")).intValue();
        int maxOccupancy = ((Long) obj.get("maxOccupancy")).intValue();
        int nights = ((Long) obj.get("nights")).intValue();
        int qualityScore = ((Long) obj.get("qualityScore")).intValue();
        int locationScore = ((Long) obj.get("locationScore")).intValue();
        double lat = (double) obj.get("lat");
        double lon = (double) obj.get("lon");
        ApartmentUnit apartmentUnit = new ApartmentUnit(name,price, maxOccupancy, nights, qualityScore, locationScore, lat, lon);
        return apartmentUnit;
    }

static AllApartments loadFromJson(Context context){
    AllApartments apartments = new AllApartments();
    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("ApartmentUnits.json"), "UTF-8")); //the encoding is optional

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(reader);
        apartments.allApartments = loadApartment(array);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return apartments;
}


    static ArrayList<ApartmentUnit> loadApartment(JSONArray array) {
        ArrayList<ApartmentUnit> root = new ArrayList<>();
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


