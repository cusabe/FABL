package com.example.propertyrentalapp;

import android.util.Log;

import com.example.propertyrentalapp.dataModels.ApartmentUnit;
import com.example.propertyrentalapp.dataModels.SearchQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.Math;

public class ApartmentUnitTest {

    ApartmentUnit unit;

    @Before
    public void setUp() {
        unit = new ApartmentUnit("AirBnB",200,2,7,5, 5, -35.282001,149.128998);
    }

    @Test
    public void unitConstructor() {
        Assert.assertEquals(unit.getName(),"AirBnB");
        Assert.assertEquals(unit.getPrice(),200);
        Assert.assertEquals(unit.getMaxOccupancy(),2);
        Assert.assertEquals(unit.getNights(),7);
        Assert.assertEquals(Math.round(unit.getLat()*1000),-35282);
        Assert.assertEquals(Math.round(unit.getLon()*1000),149129);
        Log.d("AUTest","Distance is "+Double.toString(unit.getDistance())+" metres");
    }

    @Test
    public void getSet() {
        ApartmentUnit au = new ApartmentUnit();
        au.setName("This Name");
        au.setPrice(504);
        au.setMaxOccupancy(6);
        au.setNights(29);
        au.setQualityScore(10);
        au.setLocationScore(8);
        au.setLat(-35);
        au.setLon(149);

        ApartmentUnit auCheck = new ApartmentUnit("This Name",504,6,29,10,8,-35,149);
        Assert.assertEquals(au,auCheck);
    }

    @Test
    public void testQuery() {
        ApartmentUnit auCheck = new ApartmentUnit("This Name",504,6,29,10,8,-35,149);
        SearchQuery sq = new SearchQuery();
        Assert.assertTrue(auCheck.queryMatch(sq));
    }

    @Test
    public void testDistance() {
        double distance = unit.getDistance(-35.282001, 149.128998);
        Assert.assertTrue(distance < 0.001);
    }
}
