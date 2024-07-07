package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailTiketBus_Activity extends AppCompatActivity {

    private ImageButton backButton;
    private ListView customTicketView;
    private DetailTiketBus_Adapter tiketBusAdapter;
    private TextView totalTiket; // tambahkan TextView totalTiket
    private Button paymentButton;
//    private Button keranjangButton;
    private AppDatabase db;
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
//        keranjangButton = findViewById(R.id.keranjangButton);
//        db = AppDatabase.getDatabase(this);

        backButton.setOnClickListener(view -> {
            Intent intentBackButton = new Intent(DetailTiketBus_Activity.this, PesanTiketBus_Activity.class);
            startActivity(intentBackButton);
        });

        Intent intent = getIntent();
//        ArrayList<String> selectedTickets = intent.getStringArrayListExtra("SELECTED_TICKETS");
        ArrayList<PemesananTiket> pemesananTikets = (ArrayList<PemesananTiket>) intent.getSerializableExtra("PEMESANAN_TIKETS");

        // tiketBusAdapter inisialisasi dari DetailTiketBus_Adapter untuk menampilkan ListView
        tiketBusAdapter = new DetailTiketBus_Adapter(this, pemesananTikets);
        customTicketView.setAdapter(tiketBusAdapter);

        // Mengambil total harga dari DetailTiketBus_Adapter
        int totalPrice = tiketBusAdapter.calculateTotalPrice();
        // Menetapkan total harga ke TextView totalTiket
        totalTiket.setText("Total : Rp. " + totalPrice);

        paymentButton.setOnClickListener(view -> {
            Intent intentPaymentButton = new Intent(DetailTiketBus_Activity.this, Bayar.class);
            intentPaymentButton.putExtra("PEMESANAN_TIKETS", pemesananTikets);
//            intentPaymentButton.putStringArrayListExtra("SELECTED_TICKETS", selectedTickets); // Menambahkan selectedTickets ke Intent
            startActivity(intentPaymentButton);
        });

//        keranjangButton.setOnClickListener(view -> {
//            try {
////                new InsertUserAsyncTask(db).execute(new User("John"));
//                saveTicketsToDatabase(selectedTickets);
////                Intent keranjangButton = new Intent(DetailTiketBus_Activity.this, Dashboard_Activity.class);
////                startActivity(keranjangButton);
//
//                // Show the success message
//                Toast.makeText(DetailTiketBus_Activity.this, "Berhasil Ditambahkan ke Keranjang", Toast.LENGTH_SHORT).show();
//            } catch (Exception e) {
//                Toast.makeText(this, "Error 1: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}