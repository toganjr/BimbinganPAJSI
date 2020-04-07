package com.example.bimbinganpasi.Form_Notif.Adapter;

public class DataNote {
    String dari,isi,ke,tanggal;
    int no,read_status;

    public DataNote(int no, String dari, String isi, String ke,int read_status, String tanggal) {
        this.no = no;
        this.dari = dari;
        this.isi = isi;
        this.ke = ke;
        this.read_status = read_status;
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

    public int getReadStatus() {
        return read_status;
    }

    public String getTanggal() { return tanggal;}

}

