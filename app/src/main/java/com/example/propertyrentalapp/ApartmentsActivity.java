package com.example.propertyrentalapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.propertyrentalapp.dataModels.ApartmentUnit;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class ApartmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       Intent intent = getIntent();


        ArrayList<ApartmentUnit> apartmentsToShow = (ArrayList<ApartmentUnit>) intent.getExtras().getSerializable("ApartmentList");

        setContentView(R.layout.activity_apartments);


        ListView listView = (ListView) findViewById(R.id.apartmentsListView);
        ApartmentAdapter adapter = new ApartmentAdapter(getApplicationContext(), apartmentsToShow);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getApplicationContext(), SingleResult.class);
                intent.putExtra("apartments", apartmentsToShow);
                intent.putExtra("selectedPosition", position);
                startActivity(intent);

            }
        });

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


    public class ApartmentAdapter extends BaseAdapter {

        private Context mContext;
        private List<ApartmentUnit> apartmentList = new ArrayList<>();

        public ApartmentAdapter(@NonNull Context context, ArrayList<ApartmentUnit> list) {

            mContext = context;
            apartmentList = list;
        }

        @Override
        public int getCount() {
            return apartmentList.size();
        }

        @Override
        public Object getItem(int i) {
            return apartmentList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i ;
        }

        public void setApartmentList(List<ApartmentUnit> apartmentList) {
            this.apartmentList = apartmentList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            DecimalFormat df1 = new DecimalFormat("##.#");

            View listItem = convertView;
            if(listItem == null)
                listItem = LayoutInflater.from(mContext).inflate(R.layout.column,parent,false);

            ApartmentUnit currentItem = apartmentList.get(position);

            TextView name = (TextView) listItem.findViewById(R.id.apartmentUnit);
            name.setText(currentItem.getName());

            ImageView apartmentPhoto = listItem.findViewById(R.id.apartmentPhoto);
            assignImage(currentItem, apartmentPhoto);

            TextView price = (TextView) listItem.findViewById(R.id.apartmentPrice);
            price.setText(String.valueOf("$" + currentItem.getPrice() + "/night"));

//            TextView rating = (TextView) listItem.findViewById(R.id.ratingBar);
//            rating.setText(String.valueOf("Rating: " + (float)currentItem.getQualityScore()) + "/10.0");

//            RatingBar ratingBar = listItem.findViewById(R.id.ratingBar);
//            Drawable drawable = ratingBar.getProgressDrawable();
//            drawable.setColorFilter(Color.parseColor("#FFDF00"), PorterDuff.Mode.SRC_ATOP);
//            ratingBar.setRating((float)currentItem.getQualityScore()/2);

//            TextView quality = (TextView) listItem.findViewById(R.id.apartmentQuality);
//            quality.setText(String.valueOf("Quality score: " + currentItem.getQualityScore()));
//
            TextView distance = (TextView) listItem.findViewById(R.id.ratingBar);
            distance.setText(String.valueOf("Distance: " + df1.format(currentItem.getDistance()/1000.0) + "km"));
//
//            TextView nights = (TextView) listItem.findViewById(R.id.apartmentNights);
//            nights.setText(String.valueOf("Nights: " + currentItem.getNights()));
//
//            TextView occupancy = (TextView) listItem.findViewById(R.id.apartmentOccupancy);
//            occupancy.setText(String.valueOf("Occupancy: " + currentItem.getMaxOccupancy()));



            return listItem;
        }
    }




}