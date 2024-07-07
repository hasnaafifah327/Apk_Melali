package com.example.apkmelali_test;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoice")
public class Invoice {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
    private int timestamp;

    // Constructor, getters, and setters
    public Invoice(int userId, int timestamp) {
        this.userId = 1;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
