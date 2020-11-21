package com.example.propertyrentalapp.dataModels;

import com.example.propertyrentalapp.StringSimilarity;
import com.example.propertyrentalapp.parserTokenizer.MyTokenizer;
import com.example.propertyrentalapp.parserTokenizer.Parser;
import com.example.propertyrentalapp.parserTokenizer.Exp;

public class SearchQuery {
    private SortOrder sortOrder;
    private SortVariable sortVariable;
    private int minPrice;
    private int maxPrice;
    private int minPeople;
    private int maxPeople;
    private int minNights;
    private int maxNights;
    private int minQuality;
    private int maxQuality;
    private double minDistance;
    private double maxDistance;

    // By default, search query does not restrict options
    public SearchQuery() {
        this.sortOrder = SortOrder.LOWEST;
        this.sortVariable = SortVariable.PRICE;
        this.minPrice = 0;
        this.maxPrice = 10000; //dollars per night
        this.minNights = 0;
        this.maxNights = 30;
        this.minQuality = 0;
        this.maxQuality = 11;
        this.minPeople = 0;
        this.maxPeople = 7; //dollars per night
        this.minDistance = 0; //metres
        this.maxDistance = 100000; //metres
    }

    public SortVariable getSortVariable() {
        return sortVariable;
    }

    public void setSortVariable(SortVariable sortVariable) {
        this.sortVariable = sortVariable;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(int minPeople) {
        this.minPeople = minPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getMinNights() {
        return minNights;
    }

    public void setMinNights(int minNights) {
        this.minNights = minNights;
    }

    public int getMaxNights() {
        return maxNights;
    }

    public void setMaxNights(int maxNights) {
        this.maxNights = maxNights;
    }

    public int getMinQuality() {
        return minQuality;
    }

    public void setMinQuality(int minQuality) {
        this.minQuality = minQuality;
    }

    public int getMaxQuality() {
        return maxQuality;
    }

    public void setMaxQuality(int maxQuality) {
        this.maxQuality = maxQuality;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof SearchQuery && ((SearchQuery) object).getSortOrder() == this.sortOrder && ((SearchQuery) object).getSortVariable() == this.sortVariable
                && ((SearchQuery) object).getMinPrice() == this.minPrice && ((SearchQuery) object).getMaxPrice() == this.maxPrice
                && ((SearchQuery) object).getMinPeople() == this.minPeople && ((SearchQuery) object).getMaxPeople() == this.maxPeople
                && ((SearchQuery) object).getMinNights() == this.minNights && ((SearchQuery) object).getMaxNights() == this.maxNights
                && ((SearchQuery) object).getMinQuality() == this.minQuality && ((SearchQuery) object).getMaxQuality() == this.maxQuality
                && ((SearchQuery) object).getMinDistance() == this.minDistance && ((SearchQuery) object).getMaxDistance() == this.maxDistance){
            return true;
        } else {
            return false;
        }
    }

    public void parseSearchInput(String searchQuery, SortVariable sortVariable, SortOrder sortOrder){

        StringSimilarity stringSimilarity = new StringSimilarity();

        MyTokenizer tokenizer = new MyTokenizer(searchQuery);
        Parser parser = new Parser(tokenizer);

        this.sortVariable = sortVariable;
        this.sortOrder = sortOrder;

        while (tokenizer.hasNext()) {
            Exp exp = parser.parseRange();
            if (exp == null) {
                break;
            } else if (stringSimilarity.similarity(exp.getVar(), "price") > 0.7) {
                switch (exp.eqOperator()) {
                    case "<":
                        this.maxPrice = exp.getInt();
                        break;
                    case ">":
                        this.minPrice = exp.getInt();
                        break;
                    case "=":
                        this.minPrice = exp.getInt() - 1;
                        this.maxPrice = exp.getInt() + 1;
                        break;
                }
            } else if(stringSimilarity.similarity(exp.getVar(), "quality") > 0.7){
                switch (exp.eqOperator()) {
                    case "<":
                        this.maxQuality = exp.getInt();
                        break;
                    case ">":
                        this.minQuality = exp.getInt();
                        break;
                    case "=":
                        this.minQuality = exp.getInt() - 1;
                        this.maxQuality = exp.getInt() + 1;
                        break;
                }
            } else if(stringSimilarity.similarity(exp.getVar(), "people") > 0.7) {
                switch (exp.eqOperator()) {
                    case "<":
                        this.maxPeople = exp.getInt();
                        break;
                    case ">":
                        this.minPeople = exp.getInt();
                        break;
                    case "=":
                        this.minPeople = exp.getInt() - 1;
                        //this.maxPeople = exp.getInt() + 1; // when users use people = 3 they want apartments with at least 3
                        break;
                }
            } else if(stringSimilarity.similarity(exp.getVar(), "nights") > 0.7) {
                switch (exp.eqOperator()) {
                    case "<":
                        this.maxNights = exp.getInt();
                        break;
                    case ">":
                        this.minNights = exp.getInt();
                        break;
                    case "=":
                        this.minNights = exp.getInt() - 1;
                        //this.maxNights = exp.getInt() + 1; // when users use nights = 4 they want apartments with at least 4
                        break;
                }
            } else if(stringSimilarity.similarity(exp.getVar(), "distance") > 0.7) {
                switch (exp.eqOperator()) {
                    case "<":
                        this.maxDistance = exp.getInt();
                        break;
                    case ">":
                        this.minDistance = exp.getInt();
                        break;
                    case "=":
                        this.minDistance = exp.getInt() - 1;
                        this.maxDistance = exp.getInt() + 1;
                        break;
                }
            }
        }


    }


}
