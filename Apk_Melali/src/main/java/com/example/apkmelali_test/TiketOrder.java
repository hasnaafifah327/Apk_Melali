package com.example.apkmelali_test;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "pemesanan_tiket",
        foreignKeys = @ForeignKey(
                entity = Invoice.class,
                parentColumns = "id",
                childColumns = "invoiceId",
                onDelete = ForeignKey.CASCADE))
public class TiketOrder {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int invoiceId;
    private int quantity;

    // Constructor, getters, and setters
    public TiketOrder(int invoiceId, int quantity) {
        this.invoiceId = invoiceId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
