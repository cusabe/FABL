package com.example.propertyrentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.propertyrentalapp.dataModels.ApartmentUnit;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SingleResult extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        ArrayList<ApartmentUnit> apartmentsToShow = (ArrayList<ApartmentUnit>) intent.getExtras().getSerializable("apartments");
        int position = intent.getExtras().getInt("selectedPosition");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_result);

        TextView apartmentName = findViewById(R.id.propertyName);
        TextView apartmentPrice = findViewById(R.id.txt_price);
        TextView apartmentOccupancy = findViewById(R.id.txt_occ);
        TextView apartmentNights = findViewById(R.id.txt_night2);
        TextView apartmentDistance = findViewById(R.id.txt_distance);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ImageView apartmentImage = findViewById(R.id.apartmentImage);


        DecimalFormat df1 = new DecimalFormat("##.#");

        ApartmentUnit apartmentUnitSelected = apartmentsToShow.get(position);

        assignImage(apartmentUnitSelected, apartmentImage);

        apartmentName.setText(apartmentUnitSelected.getName());
        apartmentPrice.setText("Price: $" + Integer.toString(apartmentUnitSelected.getPrice()));
        apartmentOccupancy.setText(Integer.toString(apartmentUnitSelected.getMaxOccupancy()) + " person limit");
        apartmentNights.setText(Integer.toString(apartmentUnitSelected.getNights()) + " nights");
        apartmentDistance.setText(String.valueOf(df1.format(apartmentUnitSelected.getDistance()/1000.0) + " km"));
       // apartmentDistance.setText(Math.round(apartmentUnitSelected.getDistance())+ " Km");
//        Drawable drawable = ratingBar.getProgressDrawable();
////        drawable.setColorFilter(Color.parseColor("#FFDF00"), PorterDuff.Mode.SRC_ATOP);
        ratingBar.setRating((float)apartmentUnitSelected.getQualityScore()/2);



    }

    public void assignImage(ApartmentUnit apartmentUnit, ImageView imageView){
        if(apartmentUnit.getName().contains("Hotel")){
            selectImage("hotel.jpg", imageView);
        } else if(apartmentUnit.getName().contains("Motel")){
            selectImage("motel.jpg", imageView);
        } else if(apartmentUnit.getName().contains("Resort")){
            selectImage("resort.jpg", imageView);
        } else if(apartmentUnit.getName().contains("AirBnB")){
            selectImage("airbnb.jpg", imageView);
        } else {
            selectImage("apartment.jpg", imageView);
        }
    }

    public void selectImage(String apartmentType, ImageView imageView){
        AssetManager assetManager = getAssets();
        try {
            InputStream ims = assetManager.open(apartmentType);
            Drawable d = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }
    }



}