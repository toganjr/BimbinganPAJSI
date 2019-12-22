package com.example.bimbinganpasi.Form_Menu.Adapter;

public class DataNote {
    String nim,nama,katsks,katipk;
    int semester;

    public DataNote(String nim, String nama, int semester, String katsks,String katipk) {
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
        this.katsks = katsks;
        this.katipk = katipk;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public int getSemester() {
        return semester;
    }

    public String getKatsks() {
        return katsks;
    }

    public String getKatipk() {
        return katipk;
    }

}
