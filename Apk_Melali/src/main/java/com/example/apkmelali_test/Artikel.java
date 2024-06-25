package com.example.apkmelali_test;

public class Artikel {
    private String judulArtikel;
    private String deskripsiArtikel;
    private int imageResId; // resource ID for the image

    public Artikel(String judulArtikel, String deskripsiWisata, int imageResId) {
        this.judulArtikel = judulArtikel;
        this.deskripsiArtikel = deskripsiWisata;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return judulArtikel;
    }

    public String getDescription() {
        return deskripsiArtikel;
    }

    public int getImageResId() {
        return imageResId;
    }
}
