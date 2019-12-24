package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {

    @SerializedName("no_id")
    @Expose
    private Integer noId;
    @SerializedName("nama_mhs")
    @Expose
    private String namaMhs;
    @SerializedName("nim_mhs")
    @Expose
    private String nimMhs;
    @SerializedName("semester")
    @Expose
    private Integer semester;
    @SerializedName("kategori_ipk")
    @Expose
    private String kategoriIpk;
    @SerializedName("ipk_mhs")
    @Expose
    private String ipkMhs;
    @SerializedName("kategori_sks")
    @Expose
    private String kategoriSks;
    @SerializedName("sks_mhs")
    @Expose
    private String sksMhs;

    public Integer getNoId() {
        return noId;
    }

    public void setNoId(Integer noId) {
        this.noId = noId;
    }

    public String getNamaMhs() {
        return namaMhs;
    }

    public void setNamaMhs(String namaMhs) {
        this.namaMhs = namaMhs;
    }

    public String getNimMhs() {
        return nimMhs;
    }

    public void setNimMhs(String nimMhs) {
        this.nimMhs = nimMhs;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getKategoriIpk() {
        return kategoriIpk;
    }

    public void setKategoriIpk(String kategoriIpk) {
        this.kategoriIpk = kategoriIpk;
    }

    public Object getIpkMhs() {
        return ipkMhs;
    }

    public void setIpkMhs(String ipkMhs) {
        this.ipkMhs = ipkMhs;
    }

    public String getKategoriSks() {
        return kategoriSks;
    }

    public void setKategoriSks(String kategoriSks) {
        this.kategoriSks = kategoriSks;
    }

    public String getSksMhs() {
        return sksMhs;
    }

    public void setSksMhs(String sksMhs) {
        this.sksMhs = sksMhs;
    }


}
