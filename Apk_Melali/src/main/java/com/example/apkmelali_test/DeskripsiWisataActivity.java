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

public class DeskripsiWisataActivity extends AppCompatActivity {
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

        List<DeskripsiWisata> wisataList = new ArrayList<>();
        // Tambahkan data ke wisataList dari file XML
        wisataList.add(new DeskripsiWisata("Wisata Tanah Lot", getString(R.string.description_tanah_lot), R.drawable.tanah_lot));
        wisataList.add(new DeskripsiWisata("Wisata Pantai Sanur", getString(R.string.description_pantai_sanur), R.drawable.sanur));
        wisataList.add(new DeskripsiWisata("Wisata Bedugul", getString(R.string.description_bedugul), R.drawable.bedugul));
        wisataList.add(new DeskripsiWisata("Wisata Pantai Bingin", getString(R.string.description_pantai_bingin), R.drawable.pantai_bingin));
        wisataList.add(new DeskripsiWisata("Wisata Pantai Pandawa", getString(R.string.description_pantai_pandawa), R.drawable.pandawa));
        wisataList.add(new DeskripsiWisata("Wisata Pantai Jimbaran", getString(R.string.description_pantai_jimbaran), R.drawable.pantai_jimbaran));

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
            Intent intent = new Intent(DeskripsiWisataActivity.this, Dashboard_Activity.class);
            startActivity(intent);
        });
    }
}
