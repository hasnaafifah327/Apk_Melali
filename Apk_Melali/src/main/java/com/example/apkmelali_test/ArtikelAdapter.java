package com.example.apkmelali_test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.WisataViewHolder> {

    private List<Artikel> artikelList;
    private List<Artikel> artikelListFull;
    private Context context;

    public ArtikelAdapter(List<Artikel> artikelList, Context context) {
        this.artikelList = artikelList;
        this.context = context;
        artikelListFull = new ArrayList<>(artikelList);
    }

    @NonNull
    @Override
    public WisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artikel, parent, false);
        return new WisataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WisataViewHolder holder, int position) {
        Artikel artikel = artikelList.get(position);
        holder.imageViewArtikel.setImageResource(artikel.getImageResId());
        holder.judulArtikel.setText(artikel.getTitle());

        // Menampilkan cuplikan deskripsi
        String fullDescription = artikel.getDescription();
        if (fullDescription.length() > 100) {
            holder.deskripsiArtikel.setText(fullDescription.substring(0, 100) + "...");
        } else {
            holder.deskripsiArtikel.setText(fullDescription);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailArtikelActivity.class);
            intent.putExtra("title", artikel.getTitle());
            intent.putExtra("description", artikel.getDescription());
            intent.putExtra("imageResId", artikel.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return artikelList.size();
    }

    public static class WisataViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewArtikel;
        TextView judulArtikel;
        TextView deskripsiArtikel;

        public WisataViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewArtikel = itemView.findViewById(R.id.imageArtikel);
            judulArtikel = itemView.findViewById(R.id.judul_artikel);
            deskripsiArtikel = itemView.findViewById(R.id.artikel);
        }
    }

    public void filter(String text) {
        artikelList.clear();
        if (text.isEmpty()) {
            artikelList.addAll(artikelListFull);
        } else {
            text = text.toLowerCase();
            for (Artikel item : artikelListFull) {
                if (item.getTitle().toLowerCase().contains(text)) {
                    artikelList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
