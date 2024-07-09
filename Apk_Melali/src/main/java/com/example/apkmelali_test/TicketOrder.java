package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TicketOrder extends AppCompatActivity {

    private ListView customListView;
    private PlaceAdapter customAdapter;
    private List<Place> placeList;
    private List<Place> filteredWisataList;
    private TextView totalTiketTextView;
    private Button checkoutButton;
    private SearchView simpleSearchView;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_order);

        customListView = findViewById(R.id.customListView);
        totalTiketTextView = findViewById(R.id.totalTiket);
        checkoutButton = findViewById(R.id.checkoutButton);
        simpleSearchView = findViewById(R.id.simpleSearchView);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(TicketOrder.this, Dashboard_Activity.class);
            startActivity(intent);
        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicketOrder.this, TicketOrderDetail.class);
                ArrayList<String> selectedTickets = getSelectedTickets();
                intent.putExtra("SELECTED_TICKETS", selectedTickets);
                startActivity(intent);
            }
        });

        // Initialize place list
        placeList = new ArrayList<>();
        placeList.add(new Place(R.drawable.tanah_lot, "Wisata Tanah Lot", "Kabupaten Tabanan, Bali", 50000));
        placeList.add(new Place(R.drawable.sanur, "Pantai Sanur", "Denpasar Selatan, Bali", 60000));
        placeList.add(new Place(R.drawable.pura_uluwatu, "Pura Luhur Uluwatu", "Kuta Selatan, Kab. Badung, Bali", 70000));

        // Copy the place list to the filtered list
        filteredWisataList = new ArrayList<>(placeList);

        // Set up adapter
        customAdapter = new PlaceAdapter(this, R.layout.activity_place_list, filteredWisataList, totalTiketTextView);
        customListView.setAdapter(customAdapter);

        // Set up search view
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private void filter(String text) {
        filteredWisataList.clear();
        if (text.isEmpty()) {
            filteredWisataList.addAll(placeList);
        } else {
            text = text.toLowerCase();
            for (Place place : placeList) {
                if (place.getTitle().toLowerCase().contains(text)) {
                    filteredWisataList.add(place);
                }
            }
        }
        customAdapter.notifyDataSetChanged();
    }

    private ArrayList<String> getSelectedTickets() {
        ArrayList<String> selectedTickets = new ArrayList<>();
        for (Place place : placeList) {
            if (place.getQuantity() > 0) {
                selectedTickets.add(place.getTitle() + "," + place.getQuantity() + "," + place.getSubTotalPrice());
            }
        }
        return selectedTickets;
    }
}