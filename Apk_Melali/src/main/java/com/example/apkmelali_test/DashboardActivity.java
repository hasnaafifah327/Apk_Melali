package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LinearLayout menu_desc_wisata = findViewById(R.id.btn_deskripsi_wisata);
        menu_desc_wisata.setOnClickListener(v -> {
            Intent intent1 = new Intent(DashboardActivity.this, DeskripsiWisataActivity.class);
            startActivity(intent1);
        });

        LinearLayout menu_pesan_tiket_wisata = findViewById(R.id.btn_tiket_wisata);
        menu_pesan_tiket_wisata.setOnClickListener(v -> {
            Intent intent2 = new Intent(DashboardActivity.this, PesanTiketWisata.class);
            startActivity(intent2);
        });

        LinearLayout menu_pesan_tiket_bus = findViewById(R.id.btn_tiket_bus);
        menu_pesan_tiket_bus.setOnClickListener(v -> {
            Intent intent3 = new Intent(DashboardActivity.this, PesanTiketBus.class);
            startActivity(intent3);
        });

        LinearLayout menu_artikel = findViewById(R.id.btn_artikel);
         menu_artikel.setOnClickListener(v -> {
            Intent intent4 = new Intent(DashboardActivity.this, ArtikelActivity.class);
            startActivity(intent4);
        });
    }
}
