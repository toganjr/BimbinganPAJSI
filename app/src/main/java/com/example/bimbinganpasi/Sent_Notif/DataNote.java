package com.example.bimbinganpasi.Sent_Notif;

public class DataNote {
    String dari,isi,ke,tanggal;
    int no;

    public DataNote(int no, String dari, String isi, String ke, String tanggal) {
        this.no = no;
        this.dari = dari;
        this.isi = isi;
        this.ke = ke;
        this.tanggal = tanggal;
    }

    public int getNo() {
        return no;
    }

    public String getDari() {
        return dari;
    }

    public String getIsi() { return isi; }

    public String getKe() {
        return ke;
    }

    public String getTanggal() { return tanggal;}

}

