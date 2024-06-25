package com.example.apkmelali_test;

public class Wisata1 {
    private String judulWisata;
    private String deskripsiWisata;
    private int imageResId; // resource ID for the image

    public Wisata1(String judulWisata, String deskripsiWisata, int imageResId) {
        this.judulWisata = judulWisata;
        this.deskripsiWisata = deskripsiWisata;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return judulWisata;
    }

    public String getDescription() {
        return deskripsiWisata;
    }

    public int getImageResId() {
        return imageResId;
    }
}
