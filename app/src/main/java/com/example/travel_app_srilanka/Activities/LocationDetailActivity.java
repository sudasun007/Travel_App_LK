package com.example.travel_app_srilanka.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class LocationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get location data from the intent
        Intent intent = getIntent();
        String locationName = intent.getStringExtra("locationName");

        // Create an intent to open the location in a map
        Uri locationUri = Uri.parse("geo:0,0?q=" + Uri.encode(locationName));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationUri);
        startActivity(mapIntent);

        finish();
    }
}
