package com.example.vanphu.qlchitieu.model;

/**
 * Created by VanPhu on 3/24/2018.
 */

public class hinhanh {
    private int hinhanh;
    private String name;

    public hinhanh(int hinhanh, String name) {
        this.hinhanh = hinhanh;
        this.name = name;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
