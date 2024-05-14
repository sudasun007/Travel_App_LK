package com.example.travel_app_srilanka.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app_srilanka.R;
import android.content.SharedPreferences;

public class ActivityProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button personalInfoBtn = findViewById(R.id.button2);
        Button reviewBtn = findViewById(R.id.ReviewBtn);
        Button bookingBtn = findViewById(R.id.BookingBtn);
        Button backToMainBtn = findViewById(R.id.button5);
        Button logoutBtn = findViewById(R.id.button6);

        personalInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ActivityProfile.this, EditPersonalInfoActivity.class));
            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        backToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ActivityProfile.this, MainActivity.class));
                finish();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();


                startActivity(new Intent(ActivityProfile.this, ActivityLogin.class));

                finish();
            }
        });

    }
}
