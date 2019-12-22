package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Matkul {

    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("nama_matkul")
    @Expose
    private String namaMatkul;
    @SerializedName("no_id_prasyarat")
    @Expose
    private Integer noIdPrasyarat;
    @SerializedName("nama_prasyarat")
    @Expose
    private String namaPrasyarat;
    @SerializedName("sks")
    @Expose
    private Integer sks;
    @SerializedName("semester")
    @Expose
    private Integer semester;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public Integer getNoIdPrasyarat() {
        return noIdPrasyarat;
    }

    public void setNoIdPrasyarat(Integer noIdPrasyarat) {
        this.noIdPrasyarat = noIdPrasyarat;
    }

    public String getNamaPrasyarat() {
        return namaPrasyarat;
    }

    public void setNamaPrasyarat(String namaPrasyarat) {
        this.namaPrasyarat = namaPrasyarat;
    }

    public Integer getSks() {
        return sks;
    }

    public void setSks(Integer sks) {
        this.sks = sks;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

}