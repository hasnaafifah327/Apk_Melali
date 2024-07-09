package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class TicketOrderDetailConfirm extends AppCompatActivity {
    private Button paymentButton;
    private ImageButton backButton;
    private ListView customTicketView;
    private PaymentAdapter paymentAdapter;
    private TextView totalTiket;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ticket_order_detail_confirm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        paymentButton = findViewById(R.id.paymentButton);
        backButton = findViewById(R.id.backButton);
        customTicketView = findViewById(R.id.customTicketView);
        totalTiket = findViewById(R.id.totalBayar); // inisialisasi totalTiket

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "app_database").allowMainThreadQueries().build();
        appDatabase.ticketDao();

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(TicketOrderDetailConfirm.this, TicketOrderDetail.class);
            startActivity(intent);
        });

        paymentButton.setOnClickListener(view -> {
            Intent intentPaymentButton = new Intent(TicketOrderDetailConfirm.this, Dashboard_Activity.class);
            startActivity(intentPaymentButton);

            Toast.makeText(TicketOrderDetailConfirm.this, "Berhasil!", Toast.LENGTH_SHORT).show();
        });

        customTicketView = findViewById(R.id.customTicketView);
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "app_database").allowMainThreadQueries().build();

        Intent intent = getIntent();
        String dateTime = intent.getStringExtra("DATE_TIME");

        List<Ticket> ticketList = appDatabase.ticketDao().loadAllByDates(dateTime);

        paymentAdapter = new PaymentAdapter(this, ticketList);
        customTicketView.setAdapter(paymentAdapter);

        int totalPrice = paymentAdapter.updateTotalBayar();
        // Menetapkan total harga ke TextView totalTiket
        totalTiket.setText("Total : Rp. " + totalPrice);
    }

    public void updateTotalPrice() {
        int totalPrice = paymentAdapter.updateTotalBayar();
        totalTiket.setText("Total : Rp. " + totalPrice);
    }
}