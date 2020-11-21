package com.example.propertyrentalapp;

import com.example.propertyrentalapp.dataModels.SearchQuery;
import com.example.propertyrentalapp.dataModels.SortOrder;
import com.example.propertyrentalapp.dataModels.SortVariable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SearchQueryTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testSearch1() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("price < 300",SortVariable.PRICE,SortOrder.LOWEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMaxPrice(300);

        Assert.assertEquals(sq,sqCheck);
    }

    @Test
    public void testSearch2() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("price>400;",SortVariable.DISTANCE,SortOrder.LOWEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMinPrice(400);
        sqCheck.setSortVariable(SortVariable.DISTANCE);

        Assert.assertEquals(sq,sqCheck);
    }

    @Test
    public void testSearch3() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("nights = 3",SortVariable.PRICE,SortOrder.LOWEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMinNights(2);
        //sqCheck.setMaxNights(4);

        Assert.assertEquals(sq,sqCheck);
    }

    @Test
    public void testSearch4() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("people = 5",SortVariable.PRICE,SortOrder.LOWEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMinPeople(4);
        //sqCheck.setMaxPeople(6);

        Assert.assertEquals(sq,sqCheck);
    }

    @Test
    public void testSearch5() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("     distance          > 4000 ",SortVariable.PRICE,SortOrder.LOWEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMinDistance(4000);

        Assert.assertEquals(sq,sqCheck);
    }

    @Test
    public void testSearch6() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("quality > 5",SortVariable.PRICE,SortOrder.LOWEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMinQuality(5);

        Assert.assertEquals(sq,sqCheck);
    }

    @Test
    public void testSearch7() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("price < 700; quality>3; occupancy>5 ;location<7; nights = 20; skippy = 1; interpretthis > 5; bizarre = 000 ",SortVariable.PRICE,SortOrder.LOWEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMaxPrice(700);
        sqCheck.setMinQuality(3);
        //sqCheck.setMinPeople(5);
        sqCheck.setMinNights(19);
        //sqCheck.setMaxNights(21);

        Assert.assertEquals(sq,sqCheck);
    }

    @Test
    public void testSearch8() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("quality=1;quality=2;quality=3;",SortVariable.PRICE,SortOrder.LOWEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMinQuality(2);
        sqCheck.setMaxQuality(4);

        Assert.assertEquals(sq,sqCheck);
    }

    @Test
    public void testSearch9() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("quality = 4; quality > 1;",SortVariable.PRICE,SortOrder.HIGHEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMinQuality(1);
        sqCheck.setMaxQuality(5);
        sqCheck.setSortOrder(SortOrder.HIGHEST);

        Assert.assertEquals(sq,sqCheck);
    }

    @Test
    public void testSearch10() {
        SearchQuery sq = new SearchQuery();
        sq.parseSearchInput("price < 700; price > 100; price = 300; people>2 ; people < 5; people = 3;" +
                "quality < 8; quality>3;  distance=3000; distance < 6000; nights <28; nights> 3; night = 10 ",SortVariable.PRICE,SortOrder.LOWEST);

        SearchQuery sqCheck = new SearchQuery();
        sqCheck.setMaxPrice(301);
        sqCheck.setMinPrice(299);
        sqCheck.setMaxPeople(5);
        sqCheck.setMinPeople(2);
        sqCheck.setMaxQuality(8);
        sqCheck.setMinQuality(3);
        sqCheck.setMaxDistance(6000);
        sqCheck.setMinDistance(2999);

        sqCheck.setMaxNights(28);
        sqCheck.setMinNights(9);

        Assert.assertEquals(sq,sqCheck);
    }
}
