package com.example.travel_app_srilanka.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_app_srilanka.R;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.nameTextView.setText("Name: " + booking.getName());
        holder.locationTextView.setText("Location: " + booking.getLocation());
        holder.descriptionTextView.setText("Description: " + booking.getDescription());
        holder.priceTextView.setText("Price: " + booking.getPrice());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, locationTextView, descriptionTextView, priceTextView;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            locationTextView = itemView.findViewById(R.id.textViewLocation);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            priceTextView = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
