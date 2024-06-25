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

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.WisataViewHolder> {

    private List<Artikel> artikelList;
    private List<Artikel> artikelListFull;

    public ArtikelAdapter(List<Artikel> artikelList) {
        this.artikelList = artikelList;
        artikelListFull = new ArrayList<>(artikelList);
    }

    @NonNull
    @Override
    public WisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_desc_wisata, parent, false);
        return new WisataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WisataViewHolder holder, int position) {
        Artikel artikel = artikelList.get(position);
        holder.imageViewWisata.setImageResource(artikel.getImageResId());
        holder.judul_wisata.setText(artikel.getTitle());
        holder.deskripsi_wisata.setText(artikel.getDescription());
    }

    @Override
    public int getItemCount() {
        return artikelList.size();
    }

    public static class WisataViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewWisata;
        TextView judul_wisata;
        TextView deskripsi_wisata;

        public WisataViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewWisata = itemView.findViewById(R.id.imageViewWisata);
            judul_wisata = itemView.findViewById(R.id.judul_wisata);
            deskripsi_wisata = itemView.findViewById(R.id.deskripsi_wisata);
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
