package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notif {

    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("dari")
    @Expose
    private String dari;
    @SerializedName("isi")
    @Expose
    private String isi;
    @SerializedName("ke")
    @Expose
    private Integer ke;
    @SerializedName("read_status")
    @Expose
    private Integer readStatus;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public Integer getKe() {
        return ke;
    }

    public void setKe(Integer ke) {
        this.ke = ke;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

}
