package com.example.apkmelali_test;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TicketDao {
    @Query("SELECT * FROM Ticket")
    List<Ticket> getAll();

    @Query("SELECT * FROM Ticket WHERE uid IN (:userIds)")
    List<Ticket> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Ticket WHERE date_payment IN (:date)")
    List<Ticket> loadAllByDates(String date);

    @Insert
    void insert(Ticket ticket);

    @Insert
    void insertAll(Ticket... tickets);

    @Delete
    void delete(Ticket ticket);

    @Update
    void update(Ticket ticket);
}
