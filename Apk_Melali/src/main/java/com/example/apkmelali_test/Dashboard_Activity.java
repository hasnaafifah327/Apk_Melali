package com.example.apkmelali_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        LinearLayout menu_desc_wisata = findViewById(R.id.btn_deskripsi_wisata);
        menu_desc_wisata.setOnClickListener(v -> {
            Intent intent1 = new Intent(Dashboard_Activity.this, DeskripsiWisata_Activity.class);
            startActivity(intent1);
        });

        LinearLayout menu_pesan_tiket_wisata = findViewById(R.id.btn_tiket_wisata);
        menu_pesan_tiket_wisata.setOnClickListener(v -> {
            Intent intent2 = new Intent(Dashboard_Activity.this, PesanTiketWisata_Activity.class);
            startActivity(intent2);
        });

        LinearLayout menu_pesan_tiket_bus = findViewById(R.id.btn_tiket_bus);
        menu_pesan_tiket_bus.setOnClickListener(v -> {
            Intent intent3 = new Intent(Dashboard_Activity.this, PesanTiketBus_Activity.class);
            startActivity(intent3);
        });

        LinearLayout menu_artikel = findViewById(R.id.btn_artikel);
         menu_artikel.setOnClickListener(v -> {
            Intent intent4 = new Intent(Dashboard_Activity.this, Artikel_Activity.class);
            startActivity(intent4);
        });
    }

    // Metode untuk logout
    public void logout(View view) {
        // Hapus status login dari penyimpanan preferensi bersama
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        // Arahkan pengguna kembali ke halaman login
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Menghapus semua aktivitas sebelumnya
        startActivity(intent);
        finish(); // Tutup aktivitas saat ini
    }
}
