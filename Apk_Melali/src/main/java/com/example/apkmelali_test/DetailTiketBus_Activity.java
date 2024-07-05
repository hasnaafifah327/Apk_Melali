package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class DetailTiketBus_Activity extends AppCompatActivity {

    private ImageButton backButton;
    private ListView customTicketView;
    private DetailTiketBus_Adapter tiketBusAdapter;
    private TextView totalTiket; // tambahkan TextView totalTiket
    private Button paymentButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detail_tiket_bus_activity);

        // inisialisasi
        backButton = findViewById(R.id.backButton);
        customTicketView = findViewById(R.id.customTicketView);
        totalTiket = findViewById(R.id.totalTiket);
        paymentButton = findViewById(R.id.paymentButton);

        backButton.setOnClickListener(view -> {
            Intent intentBackButton = new Intent(DetailTiketBus_Activity.this, PesanTiketBus_Activity.class);
            startActivity(intentBackButton);
        });

        Intent intent = getIntent();
        ArrayList<String> selectedTickets = intent.getStringArrayListExtra("SELECTED_TICKETS");

        // tiketBusAdapter inisialisasi dari DetailTiketBus_Adapter untuk menampilkan ListView
        tiketBusAdapter = new DetailTiketBus_Adapter(this, selectedTickets);
        customTicketView.setAdapter(tiketBusAdapter);

        // Mengambil total harga dari DetailTiketBus_Adapter
        int totalPrice = tiketBusAdapter.calculateTotalPrice();
        // Menetapkan total harga ke TextView totalTiket
        totalTiket.setText("Total : Rp. " + totalPrice);

        paymentButton.setOnClickListener(view -> {
            Intent intentPaymentButton = new Intent(DetailTiketBus_Activity.this, DetailOrderBus_Activity.class);
            intentPaymentButton.putStringArrayListExtra("SELECTED_TICKETS", selectedTickets); // Menambahkan selectedTickets ke Intent
            startActivity(intentPaymentButton);
        });
    }
}