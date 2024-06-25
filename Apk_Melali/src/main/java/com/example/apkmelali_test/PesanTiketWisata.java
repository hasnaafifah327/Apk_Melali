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

public class PesanTiketWisata extends AppCompatActivity {

    private ListView customListView;
    private CustomAdapter_TiketWisata customAdapter;
    private List<Wisata> wisataList;
    private List<Wisata> filteredWisataList;
    private TextView totalTiketTextView;
    private Button checkoutButton;
    private SearchView simpleSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_tiket_wisata);

        customListView = findViewById(R.id.customListView);
        totalTiketTextView = findViewById(R.id.totalTiket);
        checkoutButton = findViewById(R.id.checkoutButton);
        simpleSearchView = findViewById(R.id.simpleSearchView);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesanTiketWisata.this, DetailTiketWisata.class);
                ArrayList<String> selectedTickets = getSelectedTickets();
                intent.putExtra("SELECTED_TICKETS", selectedTickets);
                startActivity(intent);
            }
        });

        // Initialize wisata list
        wisataList = new ArrayList<>();
        wisataList.add(new Wisata(R.drawable.tanah_lot, "Wisata Tanah Lot", "Kabupaten Tabanan, Bali", 50000));
        wisataList.add(new Wisata(R.drawable.sanur, "Pantai Sanur", "Denpasar Selatan, Bali", 60000));
        wisataList.add(new Wisata(R.drawable.pura_uluwatu, "Pura Luhur Uluwatu", "Kuta Selatan, Kab. Badung, Bali", 70000));

        // Copy the wisata list to the filtered list
        filteredWisataList = new ArrayList<>(wisataList);

        // Set up adapter
        customAdapter = new CustomAdapter_TiketWisata(this, R.layout.activity_custom_tiket_wisata_list_view, filteredWisataList, totalTiketTextView);
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
            filteredWisataList.addAll(wisataList);
        } else {
            text = text.toLowerCase();
            for (Wisata wisata : wisataList) {
                if (wisata.getTitle().toLowerCase().contains(text)) {
                    filteredWisataList.add(wisata);
                }
            }
        }
        customAdapter.notifyDataSetChanged();
    }

    private ArrayList<String> getSelectedTickets() {
        ArrayList<String> selectedTickets = new ArrayList<>();
        for (Wisata wisata : wisataList) {
            if (wisata.getQuantity() > 0) {
                selectedTickets.add(wisata.getTitle() + " - Rp. " + wisata.getSubTotalPrice());
            }
        }
        return selectedTickets;
    }
}
