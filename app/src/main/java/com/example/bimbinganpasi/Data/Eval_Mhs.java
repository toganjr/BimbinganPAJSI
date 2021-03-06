package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Eval_Mhs {

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

    public String getKategoriIpk() {
        return kategoriIpk;
    }

    public void setKategoriIpk(String kategoriIpk) {
        this.kategoriIpk = kategoriIpk;
    }

    public String getIpkMhs() {
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
