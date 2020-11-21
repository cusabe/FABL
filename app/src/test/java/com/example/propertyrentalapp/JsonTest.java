package com.example.propertyrentalapp;

import com.example.propertyrentalapp.dataModels.ApartmentUnit;
import com.github.javafaker.Faker;

import org.junit.BeforeClass;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class JsonTest {

    private final String filePath = "ApartmentUnits.json";
    private static JsonDatabase simpleDB;

    @BeforeClass
    public static void generateTestCase(){

        Faker faker = new Faker();
        Random random = new Random();

        ArrayList<ApartmentUnit> apartments = new ArrayList<>();
        String[] apartmentType = {"Hotel", "Motel", "AirBnB", "Resort", "Apartments"};


        for (int i = 0; i < 1000; i++) {

                String fakeName = faker.funnyName().name() + " " + apartmentType[random.nextInt(apartmentType.length)];;
                int maxOccupancy = random.nextInt(6 - 1 + 1) + 1;
                int qualityScore = random.nextInt(10 - 1 + 1) + 1;
                int locationScore = random.nextInt(10 - 1 + 1) + 1;
                int nightsAvailable = random.nextInt(30 - 1 + 1) + 1;
                float lat = -35.1f + random.nextFloat() * (-35.4f - -35.1f);
                float lon = 149.1f + random.nextFloat() * (149.0f - 149.1f);
                double priceRaw = (0.3 * maxOccupancy + 0.5 * qualityScore + 0.8 * locationScore) * 50;
                int priceRounded = (int) priceRaw;
                ApartmentUnit apartmentUnit = new ApartmentUnit(fakeName, priceRounded, maxOccupancy, nightsAvailable, qualityScore, locationScore, lat, lon);
                apartments.add(apartmentUnit);
            }

        simpleDB = new JsonDatabase(apartments);
    }

    // Test JSON saving/loading (compare initial and end results)
    @Test
    public void testSimple(){
        File outputFile = new File(filePath);

        if (outputFile.exists()) {
            outputFile.delete();
        }

        simpleDB.saveToJson(filePath);

        // test the Json is saved or not
        assertTrue(outputFile.exists());

        JsonDatabase dBFromFile = JsonDatabase.loadFromJson(filePath);
        assertTrue(equalDB(simpleDB, dBFromFile));
    }

    //Checks whether 2 databases are equal
    public static boolean equalDB(JsonDatabase source, JsonDatabase target) {
        boolean result;
        for(int i=0; i<source.apartments.size(); i++) {
            result = compareApartments(source.apartments.get(i), target.apartments.get(i));
            if(!result) {
                return false;
            }
        }
        return true;
    }


    //Checks whether 2 apartments are equal
    private static boolean compareApartments(ApartmentUnit unit1, ApartmentUnit unit2) {
        if(!unit1.toString().equals(unit2.toString())) {
            return false;
        }
        return true;
    }
}
