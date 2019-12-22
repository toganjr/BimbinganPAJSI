package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logbook_Mhs {

    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("materi")
    @Expose
    private String materi;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("semester")
    @Expose
    private Integer semester;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

}
