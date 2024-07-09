package com.example.apkmelali_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TicketAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> tickets;

    public TicketAdapter(Context context, ArrayList<String> tickets) {
        this.context = context;
        this.tickets = tickets;
    }

    public ArrayList<String> getTickets() {
        return tickets;
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public Object getItem(int position) {
        return tickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_ticket_list, parent, false);
        }

        TextView titleView = convertView.findViewById(R.id.titleView);
        TextView amountView = convertView.findViewById(R.id.amountView);
        TextView subTotalView = convertView.findViewById(R.id.subTotalView);

        String ticket = tickets.get(position);
        String[] parts = ticket.split(",");
        String title = parts[0];
        String quantity = parts[1] + " pcs";
        String subTotal = "Rp. " + parts[2];

        titleView.setText(title);
        amountView.setText(quantity);
        subTotalView.setText(subTotal);

        return convertView;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (String ticket : tickets) {
            String[] parts = ticket.split(",");
            int subTotalPrice = Integer.parseInt(parts[2]);
            totalPrice += subTotalPrice;
        }
        return totalPrice;
    }
}


