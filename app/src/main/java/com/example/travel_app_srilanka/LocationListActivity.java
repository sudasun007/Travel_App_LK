package com.example.travel_app_srilanka;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app_srilanka.R;

public class LocationListActivity extends AppCompatActivity {

    private LinearLayout locationListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        locationListLayout = findViewById(R.id.locationListLayout);

        // Sample location names
        String[] locationNames = {"Anuradhapura, Ruwanweli Maha Seya", "Galle, Unawatuna Beach", "NULL NULL", "NULL NULL", "NULL NULL"};

        // Add location names to the LinearLayout
        for (String location : locationNames) {
            TextView textView = new TextView(this);
            textView.setText(location);
            textView.setTextSize(18);
            textView.setPadding(0, 10, 0, 10);
            locationListLayout.addView(textView);
        }
    }
}