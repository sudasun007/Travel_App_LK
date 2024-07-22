package com.example.travel_app_srilanka.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.travel_app_srilanka.Domains.PopularDomain;
import com.example.travel_app_srilanka.R;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTxt, locationTxt, bedTxt, guideTxt, wifiTxt, descriptionTxt, scoreTxt;
    private Button viewMoreBtn;
    private PopularDomain item;
    private ImageView picImg, backBtm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        setVariable();

        // Add OnClickListener to locationTxt to start LocationDetailActivity
        locationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationName = locationTxt.getText().toString();
                Uri locationUri = Uri.parse("geo:0,0?q=" + Uri.encode(locationName));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationUri);
                startActivity(mapIntent);
            }
        });


        // Add OnClickListener to viewMoreBtn to open the relevant Wikipedia page
        viewMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRelevantLink();
            }
        });

        Button bookButton = findViewById(R.id.button);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, AccommodationFormActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setVariable() {
        item = (PopularDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        locationTxt.setText(item.getLocation());
        bedTxt.setText(String.valueOf(item.getBed())); // Convert int to String
        descriptionTxt.setText(item.getDescription());
        scoreTxt.setText(String.valueOf(item.getScore()));

        if (item.isGuide()){
            guideTxt.setText("Guide");
        } else {
            guideTxt.setText("No-Guide");
        }

        if (item.isWifi()){
            wifiTxt.setText("Wifi");
        } else {
            wifiTxt.setText("No-Wifi");
        }

        int drawableResId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        backBtm.setOnClickListener(v -> finish());
    }

    private void initView() {
        titleTxt = findViewById(R.id.titleTxt);
        locationTxt = findViewById(R.id.locationTxt);
        bedTxt = findViewById(R.id.bedTxt);
        guideTxt = findViewById(R.id.guideTxt);
        wifiTxt = findViewById(R.id.wifiTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        picImg= findViewById(R.id.picImg);
        backBtm = findViewById(R.id.backBtn);
        viewMoreBtn = findViewById(R.id.viewMoreBtn);
    }

    private void openRelevantLink() {
        String title = titleTxt.getText().toString();
        String url = "";

        switch (title) {
            case "Ruwanweli Maha Seya":
                url = "https://en.wikipedia.org/wiki/Ruwanwelisaya";
                break;
            case "Unawatuna Beach":
                url = "https://en.wikipedia.org/wiki/Unawatuna";
                break;
            case "Yala National Park":
                url = "https://en.wikipedia.org/wiki/Yala_National_Park";
                break;
            default:
                break;
        }

        if (!url.isEmpty()) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    }

    public void showLocation(View view) {
        // Extract the location name from locationTxt
        String locationName = locationTxt.getText().toString();

        // Create a Uri to launch a map app
        Uri locationUri = Uri.parse("geo:0,0?q=" + Uri.encode(locationName));

        // Create an Intent to view the location in a map
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationUri);

        // Check if there's an app available to handle the Intent
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // Optionally handle the case where no app is available
            // For example, show a Toast message or handle it as needed
        }
    }

}
