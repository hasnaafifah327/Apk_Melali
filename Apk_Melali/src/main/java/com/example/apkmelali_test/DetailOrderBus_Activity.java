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

        // Ambil nilai dari inputQuantity yang dikirimkan melalui Intent
        Intent intent = getIntent();
        int newQuantity = intent.getIntExtra("INPUT_QUANTITY", 0);

        // Tambahkan data ke busList
        if (newQuantity > 0) {
            Bus bus = new Bus(R.drawable.bus1, "Nama Tiket", "Subtitle", newQuantity); // Ganti R.drawable.bus1 dengan sumber gambar yang sesuai
            busList.add(bus);
        }

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