package com.example.apkmelali_test;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ticket")
public class Ticket {
    @PrimaryKey(autoGenerate = true) public int uid;
    @ColumnInfo(name = "place_name") public String placeName;
    @ColumnInfo(name = "ticket_bought") public int ticketBought;
    @ColumnInfo(name = "sub_total") public int subTotal;
    @ColumnInfo(name = "date_payment") public String date_payment;

    public Ticket(String placeName, int ticketBought, int subTotal, String date_payment) {
        this.placeName = placeName;
        this.ticketBought = ticketBought;
        this.subTotal = subTotal;
        this.date_payment = date_payment;
    }
    // Constructor, getters, and setters

    public int getId() {
        return uid;
    }

    public void setId(int uid) {
        this.uid = uid;
    }

    public String getTitle() {return placeName;}

    public void setTitle(String placeName) {this.placeName = placeName;}

    public int getAmount() {
        return ticketBought;
    }

    public void setAmount(int ticketBought) {
        this.ticketBought = ticketBought;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public String getDate() {
        return date_payment;
    }

    public void setDate(String date_bought) {
        this.date_payment = date_bought;
    }
}


