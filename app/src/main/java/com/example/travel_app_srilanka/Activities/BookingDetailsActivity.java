package com.example.travel_app_srilanka.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_app_srilanka.Activities.BookingAdapter;
import com.example.travel_app_srilanka.R;

import java.util.ArrayList;
import java.util.List;

public class BookingDetailsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private List<Booking> bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewAccommodations);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookingList = new ArrayList<>();
        bookingAdapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(bookingAdapter);

        displayBookings();
    }

    private void displayBookings() {
        Cursor cursor = databaseHelper.getAllAccommodations();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));

                bookingList.add(new Booking(name, location, description, price));

            } while (cursor.moveToNext());

            cursor.close();
            bookingAdapter.notifyDataSetChanged();
        }
    }
}
