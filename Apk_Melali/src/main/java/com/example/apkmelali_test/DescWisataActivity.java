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

public class DescWisataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DeskripsiWisataAdapter wisataAdapter;
    private SearchView searchView;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_wisata);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search_view);
        backButton = findViewById(R.id.back_button);

        List<Wisata1> wisataList = new ArrayList<>();
        // Tambahkan data ke wisataList
        wisataList.add(new Wisata1("Tanah Lot", "Description 1", R.drawable.tanah_lot));
        wisataList.add(new Wisata1("Pantai Sanur", "Description 2", R.drawable.sanur));
        wisataList.add(new Wisata1("Bedugul", "Description 3", R.drawable.bedugul));
        wisataList.add(new Wisata1("Pantai Bingin", "Description 4", R.drawable.pantai_bingin));
        wisataList.add(new Wisata1("Pantai Pandawa", "Description 5", R.drawable.pandawa));
        wisataList.add(new Wisata1("Pantai Jimbaran", "Description 5", R.drawable.pantai_jimbaran));

        wisataAdapter = new DeskripsiWisataAdapter(wisataList);
        recyclerView.setAdapter(wisataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                wisataAdapter.filter(newText);
                return false;
            }

        });

        // Penginisialisasian backButton harus dilakukan setelah setContentView()
        backButton.setOnClickListener(v -> {
            // Buat intent untuk kembali ke halaman activity lain
            Intent intent = new Intent(DescWisataActivity.this, DashboardActivity.class);
            startActivity(intent);
        });
    }
}
