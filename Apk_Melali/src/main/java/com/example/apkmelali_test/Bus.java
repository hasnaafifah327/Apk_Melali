package com.example.apkmelali_test;

import java.io.Serializable;

public class Bus implements Serializable {
    private int id;
    private int image;
    private String title;
    private String subtitle;
    private int price;
    private int quantity;
    private int subTotalPrice;

    public Bus(int id, int image, String title, String subtitle, int price) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.quantity = 0;
        this.subTotalPrice = 0;
    }

    public Bus(int id, int image, String title, String subtitle, int price, int quantity, int subTotal) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.quantity = quantity;
        this.subTotalPrice = subTotal;
    }

    // Getters and Setters

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubTotalPrice() { return subTotalPrice; }

    public void setSubTotalPrice(int quantity, int price) {
        this.subTotalPrice = quantity * price;
    }

}
