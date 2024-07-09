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

public class PlaceAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Place> placeList;
    private TextView totalTiketTextView;

    public PlaceAdapter(Context context, int layout, List<Place> placeList, TextView totalTiketTextView) {
        this.context = context;
        this.layout = layout;
        this.placeList = placeList;
        this.totalTiketTextView = totalTiketTextView;
    }

    @Override
    public int getCount() {
        return placeList.size();
    }

    @Override
    public Object getItem(int position) {
        return placeList.get(position);
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

        Place place = placeList.get(position);
        holder.imageIcon.setImageResource(place.getImage());
        holder.textTitle.setText(place.getTitle());
        holder.textSubtitle.setText(place.getSubtitle());
        holder.priceText.setText("Rp. " + place.getPrice());
        holder.quantityText.setText(String.valueOf(place.getQuantity()));

        holder.addButton.setOnClickListener(v -> {
            int quantity = place.getQuantity();
            place.setQuantity(quantity + 1);
            holder.quantityText.setText(String.valueOf(place.getQuantity()));
            calculateTotalPrice();
        });

        holder.subtractButton.setOnClickListener(v -> {
            int quantity = place.getQuantity();
            if (quantity > 0) {
                place.setQuantity(quantity - 1);
                holder.quantityText.setText(String.valueOf(place.getQuantity()));
                calculateTotalPrice();
            }
        });

        return convertView;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Place wisata : placeList) {
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


