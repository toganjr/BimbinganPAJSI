package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatatanResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("metpen")
    @Expose
    private String metpen;
    @SerializedName("pkl1")
    @Expose
    private String pkl1;
    @SerializedName("pkl2")
    @Expose
    private String pkl2;
    @SerializedName("skripsi")
    @Expose
    private String skripsi;
    @SerializedName("lulus")
    @Expose
    private String lulus;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMetpen() {
        return metpen;
    }

    public void setMetpen(String metpen) {
        this.metpen = metpen;
    }

    public String getPkl1() {
        return pkl1;
    }

    public void setPkl1(String pkl1) {
        this.pkl1 = pkl1;
    }

    public String getPkl2() {
        return pkl2;
    }

    public void setPkl2(String pkl2) {
        this.pkl2 = pkl2;
    }

    public String getSkripsi() {
        return skripsi;
    }

    public void setSkripsi(String skripsi) {
        this.skripsi = skripsi;
    }

    public String getLulus() {
        return lulus;
    }

    public void setLulus(String lulus) {
        this.lulus = lulus;
    }


}
