package com.example.propertyrentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.propertyrentalapp.dataModels.ApartmentUnit;
import com.example.propertyrentalapp.dataModels.Node;
import com.example.propertyrentalapp.dataModels.SearchQuery;
import com.example.propertyrentalapp.dataModels.SortOrder;
import com.example.propertyrentalapp.dataModels.SortVariable;
import com.example.propertyrentalapp.dataModels.Tree;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Filter extends AppCompatActivity {

    private static SearchQuery query;
    private Tree tPrice, tDist; // trees sorted by Price and Dist respectively

    Button btnSearch;
    Spinner spinner;
    SortVariable sortBySelected = SortVariable.PRICE;
    SortOrder sortOrderSelected = SortOrder.LOWEST;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Code for sort by START
        ArrayList<String> sortByItemsList = new ArrayList<>();
        sortByItemsList.add("Lowest Price");
        sortByItemsList.add("Highest Price");
        sortByItemsList.add("Shortest Distance");
        sortByItemsList.add("Furthest Distance");

        spinner = findViewById(R.id.sort_by_dropdown);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sortByItemsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(item.contains("Lowest") || item.contains("Shortest")){
                    sortOrderSelected = SortOrder.LOWEST;
                } else if(item.contains("Highest") || item.contains("Furthest")){
                    sortOrderSelected = SortOrder.HIGHEST;
                }
                if(item.contains("Price")) {
                    sortBySelected = SortVariable.PRICE;
                } else if(item.contains("Distance")){
                    sortBySelected = SortVariable.DISTANCE;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Code for sort by END



        btnSearch = findViewById(R.id.buttonSearch);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        ImageView img_home = (ImageView) findViewById(R.id.img_homeSearch);
        img_home.setImageResource(R.drawable.navypic);


        // Load json database into AllApartments object
        AllApartments apartmentObj = new AllApartments();
        ArrayList<ApartmentUnit> apartmentList = apartmentObj.loadFromJson(getApplicationContext()).getListOfAllApartments();

        // Push AllApartments into the tree sorted by price
        tPrice = new Tree();
        tDist = new Tree();
        Node nodePrice;
        Node nodeDist;
        for(int i=0; i<apartmentList.size(); i++) {
            nodePrice = new Node(apartmentList.get(i));
            tPrice.insert(nodePrice);
            nodeDist = new Node(nodePrice.getApartmentUnit(),nodePrice.distKey(nodePrice.getApartmentUnit()));
            tDist.insert(nodeDist);
        }

    }



    public void searchP(View view) {
        // Capture the search string and send it to be parsed
        EditText searchString = (EditText) findViewById(R.id.editText);

        // Create a Search Query object and populate it according to the search string
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.parseSearchInput(searchString.getText().toString().toLowerCase(), sortBySelected, sortOrderSelected);

        // Search the price or distance tree to find the list of apartments that match
        ArrayList<ApartmentUnit> selectedApartments = new ArrayList<>();
        if (searchQuery.getSortVariable() == SortVariable.PRICE) {
            selectedApartments = tPrice.search(searchQuery);
        } else if (searchQuery.getSortVariable() == SortVariable.DISTANCE) {
            selectedApartments = tDist.search(searchQuery);
        }


        Intent intent = new Intent(getApplicationContext(), ApartmentsActivity.class);
        intent.putExtra("ApartmentList", selectedApartments);
        startActivity(intent);
    }

    public void helpPressed(View view) {
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivity(intent);
    }

    public void logOutPressed(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}