package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DetailTiketBus extends AppCompatActivity {

    private ImageButton backButton;
    private ListView customTicketView;
    private TiketBusAdapter tiketBusAdapter;
    private TextView totalTiket; // tambahkan TextView totalTiket

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_tiket_bus);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_detailtiketbus), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backButton = findViewById(R.id.backButton);
        customTicketView = findViewById(R.id.customTicketView);
        totalTiket = findViewById(R.id.totalTiket); // inisialisasi totalTiket

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(DetailTiketBus.this, PesanTiketBus.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        ArrayList<String> selectedTickets = intent.getStringArrayListExtra("SELECTED_TICKETS");

        tiketBusAdapter = new TiketBusAdapter(this, selectedTickets);
        customTicketView.setAdapter(tiketBusAdapter);

        // Mengambil total harga dari TicketAdapter
        int totalPrice = tiketBusAdapter.calculateTotalPrice();
        // Menetapkan total harga ke TextView totalTiket
        totalTiket.setText("Total : Rp. " + totalPrice);
    }
}

