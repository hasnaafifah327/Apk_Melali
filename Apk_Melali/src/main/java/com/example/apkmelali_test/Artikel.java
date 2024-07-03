package com.example.apkmelali_test;

public class Artikel {
    private String judulArtikel;
    private String isiArtikel;
    private int imageArtikel; // resource ID for the image

    public Artikel(String judulArtikel, String isiArtikel, int imageArtikel) {
        this.judulArtikel = judulArtikel;
        this.isiArtikel = isiArtikel;
        this.imageArtikel = imageArtikel;
    }

    public String getTitle() {
        return judulArtikel;
    }

    public String getDescription() {
        return isiArtikel;
    }

    public int getImageResId() {
        return imageArtikel;
    }
}
