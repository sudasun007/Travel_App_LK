package com.example.travel_app_srilanka.Activities;

import static com.example.travel_app_srilanka.Activities.DatabaseHelper.COLUMN_ACCOM_FROM_DATE;
import static com.example.travel_app_srilanka.Activities.DatabaseHelper.COLUMN_ACCOM_TO_DATE;
import static com.example.travel_app_srilanka.Activities.DatabaseHelper.TABLE_ACCOMMODATIONS;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app_srilanka.Activities.DatabaseHelper; // Make sure this import is correct
import com.example.travel_app_srilanka.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AccommodationFormActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText accomFromDate;
    private EditText accomToDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation_form);

        databaseHelper = new DatabaseHelper(this);

        final EditText accomName = findViewById(R.id.accomName);
        final EditText accomLocation = findViewById(R.id.accomLocation);
        final EditText accomDescription = findViewById(R.id.accomDescription);
        final EditText accomPrice = findViewById(R.id.accomPrice);
        accomFromDate = findViewById(R.id.accomFromDate);
        accomToDate = findViewById(R.id.accomToDate);
        Button submitButton = findViewById(R.id.submitButton);

        // Set up date pickers
        setupDatePickers();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = accomName.getText().toString();
                String location = accomLocation.getText().toString();
                String description = accomDescription.getText().toString();
                String price = accomPrice.getText().toString();
                String fromDate = accomFromDate.getText().toString();
                String toDate = accomToDate.getText().toString();

                // Save to database
                addAccommodation(name, location, description, price, fromDate, toDate);

                // Optionally, display a success message or navigate back
            }
        });
    }

    private void setupDatePickers() {
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        accomFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AccommodationFormActivity.this,
                        (view, year, month, dayOfMonth) -> {
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month);
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            accomFromDate.setText(dateFormat.format(calendar.getTime()));
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        accomToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AccommodationFormActivity.this,
                        (view, year, month, dayOfMonth) -> {
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month);
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            accomToDate.setText(dateFormat.format(calendar.getTime()));
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }


        });
    }

    private void addAccommodation(String name, String location, String description, String price, String fromDate, String toDate) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ACCOM_NAME, name);
        values.put(DatabaseHelper.COLUMN_ACCOM_LOCATION, location);
        values.put(DatabaseHelper.COLUMN_ACCOM_DESCRIPTION, description);
        values.put(DatabaseHelper.COLUMN_ACCOM_PRICE, price);
        values.put(COLUMN_ACCOM_FROM_DATE, fromDate);
        values.put(COLUMN_ACCOM_TO_DATE, toDate);


        long result = db.insert(TABLE_ACCOMMODATIONS, null, values);
        if (result == -1) {
            Toast.makeText(this, "Error saving accommodation", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Accommodation saved", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


}
