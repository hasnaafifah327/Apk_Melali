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

public class CustomAdapter_TiketBus extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Bus> busList;
    private TextView totalTiketTextView;

    public CustomAdapter_TiketBus(Context context, int layout, List<Bus>  busList, TextView totalTiketTextView) {
        this.context = context;
        this.layout = layout;
        this.busList = busList;
        this.totalTiketTextView = totalTiketTextView;
    }

    @Override
    public int getCount() {
        return busList.size();
    }

    @Override
    public Object getItem(int position) {
        return busList.get(position);
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

        Bus bus = busList.get(position);
        holder.imageIcon.setImageResource(bus.getImage());
        holder.textTitle.setText(bus.getTitle());
        holder.textSubtitle.setText(bus.getSubtitle());
        holder.priceText.setText("Rp. " + bus.getPrice());
        holder.quantityText.setText(String.valueOf(bus.getQuantity()));

        holder.addButton.setOnClickListener(v -> {
            int quantity = bus.getQuantity();
            bus.setQuantity(quantity + 1);
            holder.quantityText.setText(String.valueOf(bus.getQuantity()));
            calculateTotalPrice();
        });

        holder.subtractButton.setOnClickListener(v -> {
            int quantity = bus.getQuantity();
            if (quantity > 0) {
                bus.setQuantity(quantity - 1);
                holder.quantityText.setText(String.valueOf(bus.getQuantity()));
                calculateTotalPrice();
            }
        });

        return convertView;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Bus bus : busList) {
            bus.setSubTotalPrice(bus.getQuantity(), bus.getPrice());
            totalPrice += bus.getSubTotalPrice();
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


