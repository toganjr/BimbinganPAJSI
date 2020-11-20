package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifSent {

    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("dari")
    @Expose
    private Integer dari;
    @SerializedName("isi")
    @Expose
    private String isi;
    @SerializedName("ke")
    @Expose
    private String ke;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getDari() {
        return dari;
    }

    public void setDari(Integer dari) {
        this.dari = dari;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getKe() {
        return ke;
    }

    public void setKe(String ke) {
        this.ke = ke;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

}
