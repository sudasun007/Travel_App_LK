package com.example.travel_app_srilanka.Activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app_srilanka.R;

public class AccommodationFormActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation_form);

        databaseHelper = new DatabaseHelper(this);

        final EditText accomName = findViewById(R.id.accomName);
        final EditText accomLocation = findViewById(R.id.accomLocation);
        final EditText accomDescription = findViewById(R.id.accomDescription);
        final EditText accomPrice = findViewById(R.id.accomPrice);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = accomName.getText().toString();
                String location = accomLocation.getText().toString();
                String description = accomDescription.getText().toString();
                String price = accomPrice.getText().toString();

                // Save to database
                addAccommodation(name, location, description, price);

                // Optionally, display a success message or navigate back
            }
        });
    }

    private void addAccommodation(String name, String location, String description, String price) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("location", location);
        values.put("description", description);
        values.put("price", price);

        long result = db.insert("accommodations", null, values);
        if (result == -1) {
            Toast.makeText(this, "Error saving accommodation", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Accommodation saved", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}

