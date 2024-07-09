package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TicketOrderDetail extends AppCompatActivity {
    private Button paymentButton;
    private ImageButton backButton;
    private ListView customTicketView;
    private TicketAdapter ticketAdapter;
    private TextView totalTiket;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ticket_order_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        paymentButton = findViewById(R.id.paymentButton);
        backButton = findViewById(R.id.backButton);
        customTicketView = findViewById(R.id.customTicketView);
        totalTiket = findViewById(R.id.totalTiket); // inisialisasi totalTiket

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "app_database").allowMainThreadQueries().build();
        appDatabase.ticketDao();

        paymentButton.setOnClickListener(new View.OnClickListener() {
            final String formattedDate = getFormattedDate(); // declare as final
            @Override
            public void onClick(View view) {
                // Get the selected tickets from the adapter
                ArrayList<String> selectedTickets = ticketAdapter.getTickets();

                // Save the tickets to the database
                for (String ticket : selectedTickets) {
                    String[] parts = ticket.split(",");
                    String title = parts[0];
                    int amount = Integer.parseInt(parts[1]);
                    int subTotal = Integer.parseInt(parts[2]);

                    Ticket ticketList = new Ticket(title, amount, subTotal, formattedDate);;
                    appDatabase.ticketDao().insert(ticketList);
                }
                // Show a toast or something to indicate that the data has been saved
                Toast.makeText(TicketOrderDetail.this, "Data saved to database!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TicketOrderDetail.this, TicketOrderDetailConfirm.class);
                intent.putExtra("DATE_TIME", formattedDate);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(TicketOrderDetail.this, TicketOrder.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        ArrayList<String> selectedTickets = intent.getStringArrayListExtra("SELECTED_TICKETS");

        ticketAdapter = new TicketAdapter(this, selectedTickets);
        customTicketView.setAdapter(ticketAdapter);

        // Mengambil total harga dari TicketAdapter
        int totalPrice = ticketAdapter.calculateTotalPrice();
        // Menetapkan total harga ke TextView totalTiket
        totalTiket.setText("Total : Rp. " + totalPrice);
    }
    private String getFormattedDate() {
        long currentTime = System.currentTimeMillis();
        Date currentDate = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }
}





