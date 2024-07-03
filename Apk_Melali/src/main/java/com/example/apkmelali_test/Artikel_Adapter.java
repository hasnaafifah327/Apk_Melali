package com.example.apkmelali_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Artikel_Adapter extends RecyclerView.Adapter<Artikel_Adapter.WisataViewHolder> {

    private List<Artikel> artikelList;
    private List<Artikel> artikelListFull;

    public Artikel_Adapter(List<Artikel> artikelList) {
        this.artikelList = artikelList;
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
        holder.imageArtikel.setImageResource(artikel.getImageResId());
        holder.judulArtikel.setText(artikel.getTitle());
        holder.isiArtikel.setText(artikel.getDescription());
    }

    @Override
    public int getItemCount() {
        return artikelList.size();
    }

    public static class WisataViewHolder extends RecyclerView.ViewHolder {
        ImageView imageArtikel;
        TextView judulArtikel;
        TextView isiArtikel;

        public WisataViewHolder(@NonNull View itemView) {
            super(itemView);
            imageArtikel = itemView.findViewById(R.id.imageArtikel);
            judulArtikel = itemView.findViewById(R.id.judulArtikel);
            isiArtikel = itemView.findViewById(R.id.isiArtikel);
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
