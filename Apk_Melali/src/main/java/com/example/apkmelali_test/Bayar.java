package com.example.apkmelali_test;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Bayar extends AppCompatActivity {
    private TextView totalBayar;
    private Button paymentButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bayar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        totalBayar = findViewById(R.id.totalBayar);
        paymentButton = findViewById(R.id.paymentButton);
        Intent intent = getIntent();
        ArrayList<PemesananTiket> pemesananTikets = (ArrayList<PemesananTiket>) intent.getSerializableExtra("PEMESANAN_TIKETS");
        int total = 0;
        for(PemesananTiket tiket : pemesananTikets){
            total += tiket.getQuantity() * tiket.getBus().getPrice();
        }
        totalBayar.setText("Total Pembayaran: Rp. " + total + ",-");

        paymentButton.setOnClickListener(view -> {
            db = AppDatabase.getDatabase(this);

            // Insert a new invoice and save the tickets
            new InsertInvoiceAsyncTask(db, pemesananTikets).execute(new Invoice(1, (int) (System.currentTimeMillis() / 1000L)));
            Intent intentPaymentButton = new Intent(Bayar.this, Dashboard_Activity.class);
            startActivity(intentPaymentButton);

            Toast.makeText(Bayar.this, "Berhasil!", Toast.LENGTH_SHORT).show();
        });
    }

    private static class InsertInvoiceAsyncTask extends AsyncTask<Invoice, Void, Long> {
        private InvoiceDao invoiceDao;
        private ArrayList<PemesananTiket> pemesananTikets;
        private AppDatabase db;

        InsertInvoiceAsyncTask(AppDatabase db, ArrayList<PemesananTiket> pemesananTikets) {
            invoiceDao = db.invoiceDao();
            this.pemesananTikets = pemesananTikets;
            this.db = db;
        }

        @Override
        protected Long doInBackground(Invoice... invoices) {
            long invoiceId = invoiceDao.insert(invoices[0]);
            return invoiceId;
        }

        @Override
        protected void onPostExecute(Long invoiceId) {
            super.onPostExecute(invoiceId);
            // Use the invoiceId for further processing
            savePemesananTiketToDatabase(db, invoiceId.intValue(), pemesananTikets);
        }
    }

    private static void savePemesananTiketToDatabase(AppDatabase db, int invoiceId, ArrayList<PemesananTiket> pemesananTikets) {
        for (PemesananTiket tiket : pemesananTikets) {
            new InsertPemesananTiketAsyncTask(db).execute(new TiketOrder(invoiceId, tiket.getQuantity()));
        }
    }

    private static class InsertPemesananTiketAsyncTask extends AsyncTask<TiketOrder, Void, Void> {
        private TiketOrderDao tiketOrderDao;

        InsertPemesananTiketAsyncTask(AppDatabase db) {
            tiketOrderDao = db.tiketOrderDao();
        }

        @Override
        protected Void doInBackground(TiketOrder... tiketOrders) {
            tiketOrderDao.insert(tiketOrders[0]);
            return null;
        }
    }
}