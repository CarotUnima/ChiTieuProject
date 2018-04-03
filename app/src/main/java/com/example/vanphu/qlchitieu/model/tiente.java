package com.example.vanphu.qlchitieu.model;

/**
 * Created by VanPhu on 3/25/2018.
 */

public class tiente {
    private int idhinhtien;
    private String matien;

    public tiente(int idhinhtien, String matien) {
        this.idhinhtien = idhinhtien;
        this.matien = matien;
    }

    public int getIdhinhtien() {
        return idhinhtien;
    }

    public void setIdhinhtien(int idhinhtien) {
        this.idhinhtien = idhinhtien;
    }

    public String getMatien() {
        return matien;
    }

    public void setMatien(String matien) {
        this.matien = matien;
    }
}

