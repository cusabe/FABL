package com.example.propertyrentalapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("FABL");

        // Logo
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.logo);

    }

    // Login & Sign Up Button
    public void mainLoginPressed(View view) {
        Intent intent = new Intent(getApplicationContext(), userLogin.class);
        startActivity(intent);
    }

    public void mainRegPressed(View view) {
        Intent intent = new Intent(getApplicationContext(), userRegistration.class);
        startActivity(intent);
    }

    public void mainGuestPressed(View view) {
        Intent intent = new Intent(getApplicationContext(), Filter.class);
        startActivity(intent);
    }


}