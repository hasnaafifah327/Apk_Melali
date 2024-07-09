package com.example.apkmelali_test;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.room.Room;

import java.util.List;

public class PaymentAdapter extends BaseAdapter {
    private Context context;
    private List<Ticket> ticketList;
    private LayoutInflater inflater;
    private AppDatabase appDatabase;

    public PaymentAdapter(Context context, List<Ticket> ticketList) {
        this.context = context;
        this.ticketList = ticketList;
        inflater = LayoutInflater.from(context);
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "app_database").allowMainThreadQueries().build();
    }

    @Override
    public int getCount() {
        return ticketList.size();
    }

    @Override
    public Object getItem(int position) {
        return ticketList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_ticket_payment_list, parent, false);
            holder = new ViewHolder();
            holder.titleView = convertView.findViewById(R.id.titleView);
            holder.amountView = convertView.findViewById(R.id.amountView);
            holder.subTotalView = convertView.findViewById(R.id.subTotalView);
            holder.editButton = convertView.findViewById(R.id.editButton);
            holder.deleteButton = convertView.findViewById(R.id.deleteButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Ticket ticket = ticketList.get(position);
        holder.titleView.setText(ticket.getTitle());
        holder.amountView.setText(String.valueOf(ticket.getAmount()));
        holder.subTotalView.setText(String.valueOf(ticket.getSubTotal()));

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ticket ticketToEdit = ticketList.get(position);
                showEditDialog(ticketToEdit);
            }
        });
        holder.editButton.setVisibility(View.VISIBLE);

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ticket ticketToDelete = ticketList.get(position);
                appDatabase.ticketDao().delete(ticketToDelete);
                ticketList.remove(position);
                TicketOrderDetailConfirm activity = (TicketOrderDetailConfirm) context;
                activity.updateTotalPrice();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private void showEditDialog(Ticket ticket) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_ticket_edit_dialog);

        final EditText inputQuantity = dialog.findViewById(R.id.inputQuantity);
        Button saveButton = dialog.findViewById(R.id.saveButton);
        Button cancelButton = dialog.findViewById(R.id.cancelButton);

        inputQuantity.setText(String.valueOf(ticket.getAmount()));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newAmount = Integer.parseInt(inputQuantity.getText().toString());
                int previousAmount = ticket.getAmount();
                int previousSubTotal = ticket.getSubTotal();

                ticket.setAmount(newAmount);
                ticket.setSubTotal(previousSubTotal / previousAmount * newAmount);
                appDatabase.ticketDao().update(ticket);
                notifyDataSetChanged();
                // Update the total price
                TicketOrderDetailConfirm activity = (TicketOrderDetailConfirm) context;
                activity.updateTotalPrice();
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public int updateTotalBayar() {
        int totalBayar = 0;
        for (Ticket ticket : ticketList) {
            totalBayar += ticket.getSubTotal();
        }
        return totalBayar;
    }

    private class ViewHolder {
        TextView titleView;
        TextView amountView;
        TextView subTotalView;
        Button deleteButton;
        Button editButton;
    }
}