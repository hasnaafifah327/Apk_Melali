package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PesanTiketBus_Activity extends AppCompatActivity {

    private ListView customListView;
    private TiketBus_CustomAdapter customAdapter;
    private List<Bus> busList;
    private List<Bus> filteredBusList;
    private TextView totalTiket;
    private Button checkoutButton;
    private SearchView simpleSearchView;
    private ArrayList<PemesananTiket> pemesananTikets;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesan_tiket_bus_activity);

        // Menginisialisasi beberapa elemen yang ada di pesan_tiket_bus.xml
        customListView = findViewById(R.id.customListView);
        totalTiket = findViewById(R.id.totalTiket);
        checkoutButton = findViewById(R.id.checkoutButton);
        simpleSearchView = findViewById(R.id.simpleSearchView);
        backButton = findViewById(R.id.backButton);

        //objek ArrayList di inisialisasi, dan ArrayList selectedTickets dipake utk menyimpan data tiket
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(PesanTiketBus_Activity.this, Dashboard_Activity.class);
            startActivity(intent);
        });

        checkoutButton.setOnClickListener(view -> {
            Intent intent = new Intent(PesanTiketBus_Activity.this, DetailTiketBus_Activity.class);
            ArrayList<String> selectedTickets = getSelectedTickets(); // Menginisialisasi variabel selectedTickets dengan hasil dari getSelectedTickets() kemudian datanya dikirim ke DetailTiketBus
            for (PemesananTiket tiket : pemesananTikets) {
                Log.d("PesanTiketBus_Activity", "Bus ID: " + tiket.getBus().getId());
                Log.d("PesanTiketBus_Activity", "Bus Quantity: " + tiket.getQuantity());
            }

//            intent.putExtra("SELECTED_TICKETS", selectedTickets);
            intent.putExtra("PEMESANAN_TIKETS", pemesananTikets);
            startActivity(intent); //dibuat dan diisi dengan data tiket yang ingin dikirim
        });

        // Initialize daftar bus
        busList = new ArrayList<>();
        busList.add(new Bus(1, R.drawable.bus1, "BUS A", "Tabanan-Uluwatu", 50000));
        busList.add(new Bus(2, R.drawable.bus2, "BUS B", "Denpasar-Ubud", 60000));
        busList.add(new Bus(3, R.drawable.bus3, "BUS C", "Jimbaran-Ubud", 70000));
        pemesananTikets = new ArrayList<>();
        for (Bus bus : busList){
            pemesananTikets.add(new PemesananTiket(bus, 0));
        }

        // Membuat salinan dari busList ke filteredBusList, agar filteredBusList bisa dimodifikasi untuk pencarian tanpa mempengaruhi busList
        filteredBusList = new ArrayList<>(busList);

        // Set up adapter untuk menghubungkan data dari Bus, agar bisa tampil di customListView
        customAdapter = new TiketBus_CustomAdapter(this, R.layout.tiket_bus_custom, filteredBusList, totalTiket, pemesananTikets);
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
                selectedTickets.add(bus.getTitle() + ";" + bus.getPrice() + ";" + bus.getQuantity() + ";" + bus.getSubTotalPrice());
            }
        }
        return selectedTickets;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Calculate total quantity of tickets ordered
        int totalQuantity = 0;
        for (Bus bus : busList) {
            totalQuantity += bus.getQuantity();
        }
    }
}
