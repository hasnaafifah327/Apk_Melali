package com.example.apkmelali_test;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailDeskripsiWisataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_deskripsi_wisata);

        ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
        TextView judulDetail = findViewById(R.id.judulDetail);
        TextView deskripsiDetail = findViewById(R.id.deskripsiDetail);

        // Ambil data dari intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String judul = bundle.getString("judul");
            String deskripsi = bundle.getString("deskripsi");
            int imageResId = bundle.getInt("imageResId");

            judulDetail.setText(judul);
            deskripsiDetail.setText(deskripsi);
            imageViewDetail.setImageResource(imageResId);
        }
    }
}
