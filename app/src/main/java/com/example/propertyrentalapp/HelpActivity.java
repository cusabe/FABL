package com.example.propertyrentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.propertyrentalapp.helpScreens.BrowseTopic1;
import com.example.propertyrentalapp.helpScreens.BrowseTopic2;
import com.example.propertyrentalapp.helpScreens.BrowseTopic3;
import com.example.propertyrentalapp.helpScreens.BrowseTopic4;
import com.example.propertyrentalapp.helpScreens.FAQTopic1;
import com.example.propertyrentalapp.helpScreens.FAQTopic2;
import com.example.propertyrentalapp.helpScreens.FAQTopic3;
import com.example.propertyrentalapp.helpScreens.FAQTopic4;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

    ListView list1, list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        this.setTitle("Get help from our team");

        list1 = (ListView) findViewById(R.id.list1);
        list2 = (ListView) findViewById(R.id.list2);

        ArrayList<String> arrayList1 = new ArrayList<>();

        arrayList1.add("How do i use the search bar?");
        arrayList1.add("How do i make a booking?");
        arrayList1.add("How do i create an account?");
        arrayList1.add("How do i cancel my bookings?");

        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList1);

        list1.setAdapter(arrayAdapter1);

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Intent intent = new Intent(getApplicationContext(), FAQTopic1.class);
                    startActivity(intent);
                }
                if(position == 1) {
                    Intent intent = new Intent(getApplicationContext(), FAQTopic2.class);
                    startActivity(intent);
                }
                if(position == 2) {
                    Intent intent = new Intent(getApplicationContext(), FAQTopic3.class);
                    startActivity(intent);
                }
                if(position == 3) {
                    Intent intent = new Intent(getApplicationContext(), FAQTopic4.class);
                    startActivity(intent);
                }
            }
        });


        ArrayList<String> arrayList2 = new ArrayList<>();

        arrayList2.add("Your safety and privacy");
        arrayList2.add("Service Fees");
        arrayList2.add("Hosting Stays");
        arrayList2.add("Covid-19 Restrictions");

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList2);

        list2.setAdapter(arrayAdapter2);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), BrowseTopic1.class);
                    startActivity(intent);
                }
                if(position == 1) {
                    Intent intent = new Intent(getApplicationContext(), BrowseTopic2.class);
                    startActivity(intent);
                }
                if(position == 2) {
                    Intent intent = new Intent(getApplicationContext(), BrowseTopic3.class);
                    startActivity(intent);
                }
                if(position == 3) {
                    Intent intent = new Intent(getApplicationContext(), BrowseTopic4.class);
                    startActivity(intent);
                }
            }
        });
    }

}