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

public class DetailTiketWisata extends AppCompatActivity {

    private ImageButton backButton;
    private ListView customTicketView;
    private TicketAdapter ticketAdapter;
    private TextView totalTiket; // tambahkan TextView totalTiket

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_tiket_wisata);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backButton = findViewById(R.id.backButton);
        customTicketView = findViewById(R.id.customTicketView);
        totalTiket = findViewById(R.id.totalTiket); // inisialisasi totalTiket

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(DetailTiketWisata.this, PesanTiketWisata.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        ArrayList<String> selectedTickets = intent.getStringArrayListExtra("SELECTED_TICKETS");

        ticketAdapter = new TicketAdapter(this, selectedTickets);
        customTicketView.setAdapter(ticketAdapter);

        // Mengambil total harga dari TicketAdapter
        int totalPrice = ticketAdapter.calculateTotalPrice();
        // Menetapkan total harga ke TextView totalTiket
        totalTiket.setText("Total : Rp. " + totalPrice);
    }
}

