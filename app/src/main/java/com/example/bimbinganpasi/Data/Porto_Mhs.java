package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Porto_Mhs {

    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("kegiatan")
    @Expose
    private String kegiatan;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("semester")
    @Expose
    private String semester;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

}
