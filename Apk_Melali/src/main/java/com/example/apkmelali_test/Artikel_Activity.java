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

public class Artikel_Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Artikel_Adapter artikelAdapter;
    private SearchView searchView;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artikel_activity);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search_view);
        backButton = findViewById(R.id.back_button);

        List<Artikel> artikelList = new ArrayList<>();
        // Tambahkan data ke wisataList
        artikelList.add(new Artikel("Tanah Lot", "Description 1", R.drawable.tanah_lot));
        artikelList.add(new Artikel("Pantai Sanur", "Description 2", R.drawable.sanur));
        artikelList.add(new Artikel("Bedugul", "Description 3", R.drawable.bedugul));
        artikelList.add(new Artikel("Pantai Bingin", "Description 4", R.drawable.pantai_bingin));
        artikelList.add(new Artikel("Pantai Pandawa", "Description 5", R.drawable.pandawa));
        artikelList.add(new Artikel("Pantai Jimbaran", "Description 5", R.drawable.pantai_jimbaran));

        artikelAdapter = new Artikel_Adapter(artikelList);
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

        // Penginisialisasian backButton harus dilakukan setelah setContentView()
        backButton.setOnClickListener(v -> {
            // Buat intent untuk kembali ke halaman activity lain
            Intent intent = new Intent(Artikel_Activity.this, Dashboard_Activity.class);
            startActivity(intent);
        });
    }
}
