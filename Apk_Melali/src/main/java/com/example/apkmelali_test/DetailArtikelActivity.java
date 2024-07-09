package com.example.apkmelali_test;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailArtikelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        ImageView imageViewArtikel = findViewById(R.id.imageViewArtikelDetail);
        TextView judulArtikel = findViewById(R.id.judulArtikelDetail);
        TextView deskripsiArtikel = findViewById(R.id.deskripsiArtikelDetail);

        // Ambil data dari Intent
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        int imageResId = getIntent().getIntExtra("imageResId", -1);

        // Set data ke View
        judulArtikel.setText(title);
        deskripsiArtikel.setText(description);
        imageViewArtikel.setImageResource(imageResId);
    }
}
