package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Matkul_Mhs {

    @SerializedName("no")
    @Expose
    private String no;
    @SerializedName("nama_matkul")
    @Expose
    private String namaMatkul;
    @SerializedName("sks")
    @Expose
    private String sks;
    @SerializedName("nama_matkul_prasyarat")
    @Expose
    private String matkulPrasyarat;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("realisasi")
    @Expose
    private String realisasi;
    @SerializedName("nxk")
    @Expose
    private String nxk;


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getMatkulPrasyarat() {
        return matkulPrasyarat;
    }

    public void setMatkulPrasyarat(String matkulPrasyarat) { this.matkulPrasyarat = matkulPrasyarat; }

    public String getTarget() { return target; }

    public void setTarget(String target) { this.target = target; }

    public String getRealisasi() {
        return realisasi;
    }

    public void setRealisasi(String realisasi) {
        this.realisasi = realisasi;
    }

    public String getNxk() {
        return nxk;
    }

    public void setNxk(String nxk) {
        this.nxk = nxk;
    }


}
