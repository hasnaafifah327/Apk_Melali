package com.example.apkmelali_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TiketWisata_CustomAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Wisata> wisataList;
    private TextView totalTiketTextView;

    public TiketWisata_CustomAdapter(Context context, int layout, List<Wisata> wisataList, TextView totalTiketTextView) {
        this.context = context;
        this.layout = layout;
        this.wisataList = wisataList;
        this.totalTiketTextView = totalTiketTextView;
    }

    @Override
    public int getCount() {
        return wisataList.size();
    }

    @Override
    public Object getItem(int position) {
        return wisataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.imageIcon = convertView.findViewById(R.id.imageIcon);
            holder.textTitle = convertView.findViewById(R.id.textTitle);
            holder.textSubtitle = convertView.findViewById(R.id.textSubtitle);
            holder.priceText = convertView.findViewById(R.id.priceText);
            holder.quantityText = convertView.findViewById(R.id.quantityText);
            holder.addButton = convertView.findViewById(R.id.addButton);
            holder.subtractButton = convertView.findViewById(R.id.subtractButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Wisata wisata = wisataList.get(position);
        holder.imageIcon.setImageResource(wisata.getImage());
        holder.textTitle.setText(wisata.getTitle());
        holder.textSubtitle.setText(wisata.getSubtitle());
        holder.priceText.setText("Rp. " + wisata.getPrice());
        holder.quantityText.setText(String.valueOf(wisata.getQuantity()));

        holder.addButton.setOnClickListener(v -> {
            int quantity = wisata.getQuantity();
            wisata.setQuantity(quantity + 1);
            holder.quantityText.setText(String.valueOf(wisata.getQuantity()));
            calculateTotalPrice();
        });

        holder.subtractButton.setOnClickListener(v -> {
            int quantity = wisata.getQuantity();
            if (quantity > 0) {
                wisata.setQuantity(quantity - 1);
                holder.quantityText.setText(String.valueOf(wisata.getQuantity()));
                calculateTotalPrice();
            }
        });

        return convertView;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Wisata wisata : wisataList) {
            wisata.setSubTotalPrice(wisata.getQuantity(), wisata.getPrice());
            totalPrice += wisata.getSubTotalPrice();
        }
        totalTiketTextView.setText("Total Tiket : Rp. " + totalPrice);
        return totalPrice;
    }

    static class ViewHolder {
        ImageView imageIcon;
        TextView textTitle;
        TextView textSubtitle;
        TextView priceText;
        TextView quantityText;
        ImageButton addButton;
        ImageButton subtractButton;
    }
}


