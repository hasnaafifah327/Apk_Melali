package com.example.apkmelali_test;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TiketOrderDao {
    @Insert
    void insert(TiketOrder tiketOrder);

    @Query("SELECT * FROM pemesanan_tiket WHERE id = :id")
    TiketOrder getInvoiceById(int id);
}
