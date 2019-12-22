package com.example.bimbinganpasi.Form_03;

public class DataNote {

    int no;
    String kegiatan,keterangan,kategori,semester;

    public DataNote(int no, String kegiatan, String keterangan, String kategori, String semester) {
        this.no = no;
        this.kegiatan = kegiatan;
        this.keterangan = keterangan;
        this.kategori = kategori;
        this.semester = semester;
    }

    public int getNo() { return no;}

    public String getKegiatan() {
        return kegiatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getKategori() {
        return kategori;
    }

    public String getSemester() {
        return semester;
    }

}
