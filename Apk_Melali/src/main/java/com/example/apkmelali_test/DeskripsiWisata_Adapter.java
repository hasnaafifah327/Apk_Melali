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

public class DeskripsiWisata_Adapter extends RecyclerView.Adapter<DeskripsiWisata_Adapter.WisataViewHolder> {

    private List<DeskripsiWisata> wisataList;
    private List<DeskripsiWisata> wisataListFull;

    public DeskripsiWisata_Adapter(List<DeskripsiWisata> wisataList) {
        this.wisataList = wisataList;
        wisataListFull = new ArrayList<>(wisataList);
    }

    @NonNull
    @Override
    public WisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_deskripsi_wisata, parent, false);
        return new WisataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WisataViewHolder holder, int position) {
        DeskripsiWisata wisata = wisataList.get(position);
        holder.imageViewWisata.setImageResource(wisata.getImageResId());
        holder.judul_wisata.setText(wisata.getTitle());
        holder.deskripsi_wisata.setText(wisata.getDescription());
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
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
        wisataList.clear();
        if (text.isEmpty()) {
            wisataList.addAll(wisataListFull);
        } else {
            text = text.toLowerCase();
            for (DeskripsiWisata item : wisataListFull) {
                if (item.getTitle().toLowerCase().contains(text)) {
                    wisataList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
