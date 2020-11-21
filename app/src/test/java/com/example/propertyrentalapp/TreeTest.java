package com.example.propertyrentalapp;
import android.util.Log;

import com.example.propertyrentalapp.dataModels.ApartmentUnit;
import com.example.propertyrentalapp.dataModels.Node;
import com.example.propertyrentalapp.dataModels.SearchQuery;
import com.example.propertyrentalapp.dataModels.SortOrder;
import com.example.propertyrentalapp.dataModels.SortVariable;
import com.example.propertyrentalapp.dataModels.Tree;
import com.github.javafaker.Faker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TreeTest {

    Tree t;
    ApartmentUnit unit, u2, u3;
    int nTest;

    @Before
    public void setUp() {
        unit = new ApartmentUnit("AirBnB",200,2,7,5, 5, -35.282001,149.128998);
        u2 = new ApartmentUnit("AirBnB",300,3,14,4, 3, -35.493451,149.128998);
        u3 = new ApartmentUnit("AirBnB",400,4,21,3, 2, -35.662342,149.128998);

    }

    @Test
    public void test01InsertOnce() {
        Tree t_small = new Tree();
        t_small.insert(new Node(unit, 5));
        Assert.assertEquals(t_small.getRoot().getKey(),5);
    }

    @Test
    public void test02InsertMany() {
        Tree t_small = new Tree();
        t_small.insert(new Node(unit, 5));
        t_small.insert(new Node(unit, 8));
        t_small.insert(new Node(unit, 3));
        t_small.insert(new Node(unit, 7));

        Assert.assertEquals(t_small.getRoot().right.getKey(),8);
        Assert.assertEquals(t_small.getRoot().left.getKey(),3);
        Assert.assertEquals(t_small.getRoot().right.left.getKey(),7);
    }

    @Test
    public void test03TreeSize() {
        Tree t_small = new Tree();
        Assert.assertEquals(t_small.size(),0);
        t_small.insert(new Node(unit, 5));
        Assert.assertEquals(t_small.size(),1);
        t_small.insert(new Node(unit, 8));
        Assert.assertEquals(t_small.size(),2);
        t_small.insert(new Node(unit, 3));
        Assert.assertEquals(t_small.size(),3);
        t_small.insert(new Node(unit, 7));
        Assert.assertEquals(t_small.size(),4);
    }

    @Test
    public void test04TreeHeight() {
        Tree t_small = new Tree();
        Assert.assertEquals(t_small.height(),-1);
        t_small.insert(new Node(unit, 5));
        Assert.assertEquals(t_small.height(),0);
        t_small.insert(new Node(unit, 8));
        Assert.assertEquals(t_small.height(),1);
        t_small.insert(new Node(unit, 3));
        Assert.assertEquals(t_small.height(),1);
        t_small.insert(new Node(unit, 7));
        Assert.assertEquals(t_small.height(),2);
    }

    @Test
    public void test05TreePreOrderList() {
        Tree t_small = new Tree();
        t_small.insert(new Node(unit, 5));
        t_small.insert(new Node(unit, 8));
        t_small.insert(new Node(unit, 3));
        t_small.insert(new Node(unit, 7));
        ArrayList<ApartmentUnit> testList = t_small.preOrderList();
        for (ApartmentUnit ApUn : testList) {
            Assert.assertEquals(ApUn.getPrice(),200);
        }

    }

    @Test
    public void test06PriceKey() {
        Tree t_small = new Tree();
        t_small.insert(new Node(unit));
        Assert.assertEquals(t_small.getRoot().getKey(), 20001110);
        t_small.insert(new Node(u2));
        Assert.assertEquals(t_small.getRoot().right.getKey(), 30024011);
        t_small.insert(new Node(u3));
        Assert.assertEquals(t_small.getRoot().right.right.getKey(), 40042782);
    }

    @Test
    public void test07StartFaker() {
        Faker faker = new Faker();
        Random random = new Random();

        nTest = 1000;
        t = new Tree();

        // Generate nTest random apartments and add them to the tree

        for (int i = 0; i < nTest; i++) {
            Assert.assertEquals(t.size(), i);

            String fakeName = faker.company().name();
            int maxOccupancy = random.nextInt(6 - 1 + 1) + 1;
            int qualityScore = random.nextInt(10 - 1 + 1) + 1;
            int locationScore = random.nextInt(10 - 1 + 1) + 1;
            int nightsAvailable = random.nextInt(30 - 7 + 1) + 7;
            float lat = -35.1f + random.nextFloat() * (-35.4f - -35.1f);
            float lon = 149.1f + random.nextFloat() * (149.0f - 149.1f);
            double priceRaw = (0.3 * maxOccupancy + 0.5 * qualityScore + 0.8 * locationScore) * 50;
            int priceRounded = (int) priceRaw;
            ApartmentUnit apartmentUnit = new ApartmentUnit(fakeName, priceRounded, maxOccupancy, nightsAvailable, qualityScore, locationScore, lat, lon);

            Node node = new Node(apartmentUnit);
            t.insert(node);
        }

        // Report the tree dimensions at the end
        Assert.assertEquals(t.size(), nTest);


    }

    @Test
    public void test08LoadNRecords() {
        Faker faker = new Faker();
        Random random = new Random();

        nTest = 1000;
        t = new Tree();

        // Generate nTest random apartments and add them to the tree

        for (int i = 0; i < nTest; i++) {
            Assert.assertEquals(t.size(), i);

            String fakeName = faker.company().name();
            int maxOccupancy = random.nextInt(6 - 1 + 1) + 1;
            int qualityScore = random.nextInt(10 - 1 + 1) + 1;
            int locationScore = random.nextInt(10 - 1 + 1) + 1;
            int nightsAvailable = random.nextInt(30 - 7 + 1) + 7;
            float lat = -35.1f + random.nextFloat() * (-35.4f - -35.1f);
            float lon = 149.1f + random.nextFloat() * (149.0f - 149.1f);
            double priceRaw = (0.3 * maxOccupancy + 0.5 * qualityScore + 0.8 * locationScore) * 50;
            int priceRounded = (int) priceRaw;
            ApartmentUnit apartmentUnit = new ApartmentUnit(fakeName, priceRounded, maxOccupancy, nightsAvailable, qualityScore, locationScore, lat, lon);

            Node node = new Node(apartmentUnit);
            t.insert(node);
        }

        // Report the tree dimensions at the end
        Assert.assertEquals(t.size(), nTest);


    }



    @Test
    public void test09InOrderListNRecords() {
        Faker faker = new Faker();
        Random random = new Random();


        nTest = 1000;
        t = new Tree();

        // Generate nTest random apartments and add them to the tree

        for (int i = 0; i < nTest; i++) {
            Assert.assertEquals(t.size(), i);

            String fakeName = faker.company().name();
            int maxOccupancy = random.nextInt(6 - 1 + 1) + 1;
            int qualityScore = random.nextInt(10 - 1 + 1) + 1;
            int locationScore = random.nextInt(10 - 1 + 1) + 1;
            int nightsAvailable = random.nextInt(30 - 7 + 1) + 7;
            float lat = -35.1f + random.nextFloat() * (-35.4f - -35.1f);
            float lon = 149.1f + random.nextFloat() * (149.0f - 149.1f);
            double priceRaw = (0.3 * maxOccupancy + 0.5 * qualityScore + 0.8 * locationScore) * 50;
            int priceRounded = (int) priceRaw;
            ApartmentUnit apartmentUnit = new ApartmentUnit(fakeName, priceRounded, maxOccupancy, nightsAvailable, qualityScore, locationScore, lat, lon);

            Node node = new Node(apartmentUnit);
            t.insert(node);
        }

        // Report the list of prices at the end inOrder and confirm each is not more expensive than the previous
        ArrayList<ApartmentUnit> testList = t.inOrderList();
        int prevPrice = 0;
        int thisPrice;
        for (ApartmentUnit ApUn : testList) {
            thisPrice = ApUn.getPrice();
            Assert.assertTrue("inOrderList",(thisPrice>=prevPrice));
            Log.d("Tree","Price is " + Integer.toString(thisPrice));
            prevPrice = thisPrice;
        }

    }

    @Test
    public void test10SearchLowestPriceNRecords() {
        Faker faker = new Faker();
        Random random = new Random();

        nTest = 1000;
        t = new Tree();

        // Generate nTest random apartments and add them to the tree

        for (int i = 0; i < nTest; i++) {
            Assert.assertEquals(t.size(), i);

            String fakeName = faker.company().name();
            int maxOccupancy = random.nextInt(6 - 1 + 1) + 1;
            int qualityScore = random.nextInt(10 - 1 + 1) + 1;
            int locationScore = random.nextInt(10 - 1 + 1) + 1;
            int nightsAvailable = random.nextInt(30 - 7 + 1) + 7;
            float lat = -35.1f + random.nextFloat() * (-35.4f - -35.1f);
            float lon = 149.1f + random.nextFloat() * (149.0f - 149.1f);
            double priceRaw = (0.3 * maxOccupancy + 0.5 * qualityScore + 0.8 * locationScore) * 50;
            int priceRounded = (int) priceRaw;
            ApartmentUnit apartmentUnit = new ApartmentUnit(fakeName, priceRounded, maxOccupancy, nightsAvailable, qualityScore, locationScore, lat, lon);

            Node node = new Node(apartmentUnit);
            t.insert(node);
        }
        //  Set up a few search queries and see if they generate good data
        SearchQuery sq = new SearchQuery();
        sq.setMinPrice(200);
        sq.setMaxPrice(400);
        sq.setMaxDistance(10000);


        // Occupies at least 4 people
        sq.setMinPeople(3);

        // Quality at least 3 stars - 6 half stars
        sq.setMinQuality(5);

        // Use this search query to look for apartments
        ArrayList<ApartmentUnit> testList = t.searchLowest(sq);

        // List the prices of these apartments
        Log.d("Tree","Apartments found by search query: " + Integer.toString(testList.size()));
        int prevPrice = 0;
        int thisPrice;
        for (ApartmentUnit ApUn : testList) {
            thisPrice = ApUn.getPrice();
            Assert.assertTrue("inOrderList",(thisPrice>=prevPrice));
            Log.d("Tree",ApUn.toString());
            prevPrice = thisPrice;
        }

    }

    @Test
    public void test11SearchHighestPriceNRecords() {
        Faker faker = new Faker();
        Random random = new Random();

        nTest = 1000;
        t = new Tree();

        // Generate nTest random apartments and add them to the tree

        for (int i = 0; i < nTest; i++) {
            Assert.assertEquals(t.size(), i);

            String fakeName = faker.company().name();
            int maxOccupancy = random.nextInt(6 - 1 + 1) + 1;
            int qualityScore = random.nextInt(10 - 1 + 1) + 1;
            int locationScore = random.nextInt(10 - 1 + 1) + 1;
            int nightsAvailable = random.nextInt(30 - 7 + 1) + 7;
            float lat = -35.1f + random.nextFloat() * (-35.4f - -35.1f);
            float lon = 149.1f + random.nextFloat() * (149.0f - 149.1f);
            double priceRaw = (0.3 * maxOccupancy + 0.5 * qualityScore + 0.8 * locationScore) * 50;
            int priceRounded = (int) priceRaw;
            ApartmentUnit apartmentUnit = new ApartmentUnit(fakeName, priceRounded, maxOccupancy, nightsAvailable, qualityScore, locationScore, lat, lon);

            Node node = new Node(apartmentUnit);
            t.insert(node);
        }
        //  Set up a few search queries and see if they generate good data
        SearchQuery sq = new SearchQuery();
        sq.setMinPrice(500);
        sq.setMaxDistance(10000);

        // Occupies no more than 3 people
        sq.setMaxPeople(4);

        // Quality no more than 2.5 stars qual of 5
        sq.setMaxQuality(6);

        // Sort order for highest price
        sq.setSortOrder(SortOrder.HIGHEST);
        sq.setSortVariable(SortVariable.PRICE);

        // Use this search query to look for apartments
        ArrayList<ApartmentUnit> testList = t.search(sq);

        // List the prices of these apartments
        Log.d("Tree","Apartments found by search query: " + Integer.toString(testList.size()));
        int prevPrice = 10000;
        int thisPrice;
        for (ApartmentUnit ApUn : testList) {
            thisPrice = ApUn.getPrice();
            Assert.assertTrue("inOrderList",(thisPrice<=prevPrice));
            Log.d("Tree",ApUn.toString());
            prevPrice = thisPrice;
        }

    }

    @Test
    public void test12LoadNRecordsintoTwoTrees() {
        Faker faker = new Faker();
        Random random = new Random();

        nTest = 1000;
        Tree tPrice = new Tree();
        Tree tDist = new Tree();

        // Generate nTest random apartments and add them to the tree

        for (int i = 0; i < nTest; i++) {
            Assert.assertEquals(tPrice.size(), i);
            Assert.assertEquals(tDist.size(), i);

            String fakeName = faker.company().name();
            int maxOccupancy = random.nextInt(6 - 1 + 1) + 1;
            int qualityScore = random.nextInt(10 - 1 + 1) + 1;
            int locationScore = random.nextInt(10 - 1 + 1) + 1;
            int nightsAvailable = random.nextInt(30 - 7 + 1) + 7;
            float lat = -35.1f + random.nextFloat() * (-35.4f - -35.1f);
            float lon = 149.1f + random.nextFloat() * (149.0f - 149.1f);
            double priceRaw = (0.3 * maxOccupancy + 0.5 * qualityScore + 0.8 * locationScore) * 50;
            int priceRounded = (int) priceRaw;
            ApartmentUnit apartmentUnit = new ApartmentUnit(fakeName, priceRounded, maxOccupancy, nightsAvailable, qualityScore, locationScore, lat, lon);

            Node nodePrice = new Node(apartmentUnit);
            Node nodeDist = new Node(apartmentUnit,nodePrice.distKey(apartmentUnit));
            tPrice.insert(nodePrice);
            tDist.insert(nodeDist);
            Log.d("Tree","PriceTree Size: " + Integer.toString(tPrice.size())
                    + " height: " + Integer.toString(tPrice.height())
                    + " key: "  + Integer.toString(nodePrice.getKey()));
            Log.d("Tree","DistTree Size: " + Integer.toString(tDist.size())
                    + " height: " + Integer.toString(tDist.height())
                    + " key: "  + Integer.toString(nodeDist.getKey()));
        }

        // Report the tree dimensions at the end
        Assert.assertEquals(tPrice.size(), nTest);
        Assert.assertEquals(tDist.size(), nTest);



    }



}
