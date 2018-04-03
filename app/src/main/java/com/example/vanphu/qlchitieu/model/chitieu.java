package com.example.vanphu.qlchitieu.model;

/**
 * Created by VanPhu on 3/26/2018.
 */

public class chitieu {
    private int id;
    private String tenchitieu;
    private int giachitieu;
    private int idhinh;
    private String ngay;
    private String email;

    public chitieu(int id, String tenchitieu, int giachitieu, int idhinh, String ngay, String email) {
        this.id = id;
        this.tenchitieu = tenchitieu;
        this.giachitieu = giachitieu;
        this.idhinh = idhinh;
        this.ngay = ngay;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenchitieu() {
        return tenchitieu;
    }

    public void setTenchitieu(String tenchitieu) {
        this.tenchitieu = tenchitieu;
    }

    public int getGiachitieu() {
        return giachitieu;
    }

    public void setGiachitieu(int giachitieu) {
        this.giachitieu = giachitieu;
    }

    public int getIdhinh() {
        return idhinh;
    }

    public void setIdhinh(int idhinh) {
        this.idhinh = idhinh;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


