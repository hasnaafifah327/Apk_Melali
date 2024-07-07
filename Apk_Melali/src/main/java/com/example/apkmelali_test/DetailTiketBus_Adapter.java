package com.example.apkmelali_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailTiketBus_Adapter extends BaseAdapter {
    private Context context; //Untuk menyediakan akses ke berbagai aspek dari aplikasi Android yang sedang berjalan.
//    private ArrayList<String> tickets; //menyimpan daftar tiket dalam bentuk array list di variable tickets
    private ArrayList<PemesananTiket> pemesananTikets;

    public DetailTiketBus_Adapter(Context context, ArrayList<PemesananTiket> pemesananTikets /*ArrayList<String> tickets*/) {
        this.context = context;
//        this.tickets = tickets;
        this.pemesananTikets = pemesananTikets;
    }

    //untuk mengetahui berapa banyak item yang harus ditampilkan dalam daftar ArrayList<String> tickets
    @Override
    public int getCount() {
        return pemesananTikets.size();
    }

    //int position : untuk mengetahui Posisi item yang ingin diambil dalam daftar tickets
    //jika position : 2, maka akan mengembalikan item yang ada di indeks ke-2 dalam ArrayList<String> tickets
    @Override
    public Object getItem(int position) {
        return pemesananTikets.get(position);
    }

    // untuk mengembalikan ID unik dari item pada posisi tertentu dalam daftar.
    //ID dari item selalu sama dengan posisi item dalam daftar tickets.
    @Override
    public long getItemId(int position) {
        return position;
    }

    //untuk membuat atau meng-update tampilan (view) item dalam list berdasarkan
    //data dari posisi tertentu dalam ArrayList<String> tickets.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.detail_tiket_bus_list_view, parent, false);
            holder = new ViewHolder();
            holder.titleView = convertView.findViewById(R.id.titleView);
            holder.quantityView = convertView.findViewById(R.id.quantityView);
            holder.subTotalView = convertView.findViewById(R.id.subTotalView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        // Mengambil data dari ArrayList<String> tickets
//        // pada posisi tertentu, dan memecah string berdasarkan delimiter
//        String ticket = tickets.get(position);
//        String[] parts = ticket.split(";");
//        String title = parts[0];
//        String subTotal = "Rp. " + parts[1];
        PemesananTiket tiket = pemesananTikets.get(position);


        //Mengatur teks
        holder.titleView.setText(tiket.getBus().getTitle());
        holder.quantityView.setText("         " + tiket.getQuantity());
        holder.subTotalView.setText(String.valueOf(tiket.getQuantity() * tiket.getBus().getPrice()));

        return convertView;
    }

    //untuk mendapatkan jumlah tiket dari judul tertentu yg ada di PesanTiketBus
    private int getQuantityFromTitle(String title) {
        // Metode untuk mendapatkan jumlah tiket dari judul,
        // mengasumsikan jumlah sudah disimpan dalam tiket
        if (TiketBus_CustomAdapter.busList != null) {
            for (Bus bus : TiketBus_CustomAdapter.busList) {
                if (bus.getTitle().equals(title)) {
                    return bus.getQuantity();
                }
            }
        }
        return 0;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
//        for (String ticket : tickets) {
//            String[] parts = ticket.split(";");
//            int subTotalPrice = Integer.parseInt(parts[3]);
//            totalPrice += subTotalPrice;
//        }
        for(PemesananTiket tiket : pemesananTikets){
            totalPrice += tiket.getQuantity() * tiket.getBus().getPrice();
        }
        return totalPrice;
    }

    static class ViewHolder {
        TextView titleView;
        TextView quantityView;
        TextView subTotalView;
    }
}
