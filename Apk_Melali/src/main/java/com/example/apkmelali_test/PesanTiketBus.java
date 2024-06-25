package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PesanTiketBus extends AppCompatActivity {

    private ListView customListView;
    private CustomAdapter_TiketBus customAdapter;
    private List<Bus> busList;
    private List<Bus> filteredBusList;
    private TextView totalTiketTextView;
    private Button checkoutButton;
    private SearchView simpleSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_tiket_bus);

        customListView = findViewById(R.id.customListView);
        totalTiketTextView = findViewById(R.id.totalTiket);
        checkoutButton = findViewById(R.id.checkoutButton);
        simpleSearchView = findViewById(R.id.simpleSearchView);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesanTiketBus.this, DetailTiketBus.class);
                ArrayList<String> selectedTickets = getSelectedTickets();
                intent.putExtra("SELECTED_TICKETS", selectedTickets);
                startActivity(intent);
            }
        });

        // Initialize wisata list
        busList = new ArrayList<>();
        busList.add(new Bus(R.drawable.bus1, "BUS A", "Tabanan-Uluwatu", 50000));
        busList.add(new Bus(R.drawable.bus2, "BUS B", "Denpasar-Ubud", 60000));
        busList.add(new Bus(R.drawable.bus3, "BUS C", "Jimbaran-Ubud", 70000));

        // Copy the wisata list to the filtered list
        filteredBusList = new ArrayList<>(busList);

        // Set up adapter
        customAdapter = new CustomAdapter_TiketBus(this, R.layout.activity_custom_tiket_bus_list_view, filteredBusList, totalTiketTextView);
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
        filteredBusList.clear();
        if (text.isEmpty()) {
            filteredBusList.addAll(busList);
        } else {
            text = text.toLowerCase();
            for (Bus bus : busList) {
                if (bus.getTitle().toLowerCase().contains(text)) {
                    filteredBusList.add(bus);
                }
            }
        }
        customAdapter.notifyDataSetChanged();
    }

    private ArrayList<String> getSelectedTickets() {
        ArrayList<String> selectedTickets = new ArrayList<>();
        for (Bus bus : busList) {
            if (bus.getQuantity() > 0) {
                selectedTickets.add(bus.getTitle() + " - Rp. " + bus.getSubTotalPrice());
            }
        }
        return selectedTickets;
    }
}
