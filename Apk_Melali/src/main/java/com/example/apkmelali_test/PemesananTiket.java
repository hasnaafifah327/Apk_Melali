package com.example.apkmelali_test;

import java.io.Serializable;

public class PemesananTiket implements Serializable {
    private Bus bus;
    private int quantity;

    public PemesananTiket(Bus bus, int quantity) {
        this.bus = bus;
        this.quantity = quantity;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
