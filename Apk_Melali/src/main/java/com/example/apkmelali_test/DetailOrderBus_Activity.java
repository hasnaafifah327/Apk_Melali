package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderBus_Activity extends AppCompatActivity {

    private ImageButton backButton;
    private ListView customTicketView;
    private DetailOrderBus_Adapter detailOrderBusAdapter;
    private TextView totalTiket;
    private Button simpanButton;

    // List untuk menyimpan objek Bus
    private List<Bus> busList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order_bus_activity);

        // Inisialisasi view
        backButton = findViewById(R.id.backButton);
        customTicketView = findViewById(R.id.customTicketView);
        totalTiket = findViewById(R.id.totalTiket);
        simpanButton = findViewById(R.id.simpanButton);

        backButton.setOnClickListener(view -> {
            Intent intentBackButton = new Intent(DetailOrderBus_Activity.this, DetailTiketBus_Activity.class);
            startActivity(intentBackButton);
        });

        // Initialize daftar bus
        busList = new ArrayList<>();
        busList.add(new Bus(1, R.drawable.bus1, "BUS A", "Tabanan-Uluwatu", 50000));
        busList.add(new Bus(2, R.drawable.bus2, "BUS B", "Denpasar-Ubud", 60000));
        busList.add(new Bus(3, R.drawable.bus3, "BUS C", "Jimbaran-Ubud", 70000));

        // Ambil nilai dari inputQuantity yang dikirimkan melalui Intent
        Intent intent = getIntent();
        ArrayList<String> selectedTickets = intent.getStringArrayListExtra("SELECTED_TICKETS");

        // Anda dapat melakukan sesuatu dengan selectedTickets di sini sesuai kebutuhan aplikasi Anda

        detailOrderBusAdapter = new DetailOrderBus_Adapter(this, busList);
        customTicketView.setAdapter(detailOrderBusAdapter);

        // Menghitung dan menampilkan harga total
        updateTotalPrice();

        simpanButton.setOnClickListener(view -> {
            // Melakukan operasi simpan atau tindakan lain
            // Setelah operasi, kembali ke halaman sebelumnya atau lakukan tindakan yang sesuai
            Intent intentPaymentButton = new Intent(DetailOrderBus_Activity.this, DetailOrderBus_Activity.class);
            startActivity(intentPaymentButton);
        });
    }

    // Metode untuk memperbarui harga total yang ditampilkan
    private void updateTotalPrice() {
        int totalPrice = detailOrderBusAdapter.calculateTotalPrice();
        totalTiket.setText("Total : Rp. " + totalPrice);
    }

    // Metode untuk mengedit item dalam daftar
    public void editItem(int position, int newQuantity) {
        if (position >= 0 && position < busList.size()) {
            Bus bus = busList.get(position);
            bus.setQuantity(newQuantity);
            bus.setSubTotalPrice(newQuantity, bus.getPrice());
            detailOrderBusAdapter.notifyDataSetChanged();
            updateTotalPrice();
        }
    }

    // Metode untuk menghapus item dari daftar
    public void deleteItem(int position) {
        if (position >= 0 && position < busList.size()) {
            busList.remove(position);
            detailOrderBusAdapter.notifyDataSetChanged();
            updateTotalPrice();
        }
    }
}
