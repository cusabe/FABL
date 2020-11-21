package com.example.propertyrentalapp.helpScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.propertyrentalapp.R;
//import com.example.propertyrentalapp.webURL;

import java.util.ArrayList;

public class BrowseTopic4 extends AppCompatActivity {

    ListView listURL;
    String webURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_topic4);
        this.setTitle("Help");

        listURL = findViewById(R.id.listURL);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("https://www.covid19.act.gov.au/");
        arrayList.add("https://www.nsw.gov.au/covid-19");
        arrayList.add("https://www.dhhs.vic.gov.au/coronavirus-covid-19-daily-update");
        arrayList.add("https://www.qld.gov.au/health/conditions/health-alerts/coronavirus-covid-19/current-status/statistics");
        arrayList.add("https://www.wa.gov.au/government/covid-19-coronavirus");
        arrayList.add("https://www.covid-19.sa.gov.au/");
        arrayList.add("https://coronavirus.tas.gov.au/");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listURL.setAdapter(arrayAdapter);

        listURL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                webURL = (listURL.getItemAtPosition(position).toString());

                Intent intent = new Intent(getApplicationContext(), webURL.class);
                intent.putExtra("URL", webURL);
                startActivity(intent);
            }
        });
    }
}