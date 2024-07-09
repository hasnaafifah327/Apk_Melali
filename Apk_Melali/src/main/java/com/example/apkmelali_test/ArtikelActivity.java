package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArtikelActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArtikelAdapter artikelAdapter;
    private SearchView searchView;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search_view);
        backButton = findViewById(R.id.back_button);

        List<Artikel> artikelList = new ArrayList<>();
        artikelList.add(new Artikel("Uluwatu Temple, Wisata dengan Spot Sunset Terbaik di Bali", getString(R.string.artikel_tanah_lot), R.drawable.tanah_lot));
        artikelList.add(new Artikel("Menikmati Keindahan Surga Bahari di Pantai Sanur Bali", getString(R.string.artikel_pantai_sanur), R.drawable.sanur));
        artikelList.add(new Artikel("Keindahan Wisata Bedugul", getString(R.string.artikel_bedugul), R.drawable.bedugul));
        artikelList.add(new Artikel("Pantai Bingin, Wisata Pantai di Selatan Bali", getString(R.string.artikel_pantai_bingin), R.drawable.pantai_bingin));
        artikelList.add(new Artikel("Pandawa Beach, wisata pantai di Tebing Bali", getString(R.string.artikel_pantai_pandawa), R.drawable.pandawa));
        artikelList.add(new Artikel("Pantai Jimbaran, pusatnya wisata Seefood ", getString(R.string.artikel_pantai_jimbaran), R.drawable.pantai_jimbaran));

        artikelAdapter = new ArtikelAdapter(artikelList, this);
        recyclerView.setAdapter(artikelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                artikelAdapter.filter(newText);
                return false;
            }
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ArtikelActivity.this, Dashboard_Activity.class);
            startActivity(intent);
        });
    }
}
