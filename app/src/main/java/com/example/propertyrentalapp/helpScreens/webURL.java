package com.example.propertyrentalapp.helpScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.propertyrentalapp.R;

public class webURL extends AppCompatActivity {

    String webURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_u_r_l);

        WebView wv = (WebView) findViewById(R.id.wv);

        Intent intent = getIntent();
        webURL = getIntent().getExtras().getString("URL", webURL);

        wv.loadUrl(webURL);

        if(webURL.matches("")) {
            Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}