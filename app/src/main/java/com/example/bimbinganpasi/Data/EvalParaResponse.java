package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvalParaResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("sks")
    @Expose
    private Integer sks;
    @SerializedName("ipk")
    @Expose
    private String ipk;
    @SerializedName("tahun")
    @Expose
    private Integer tahun;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getSks() {
        return sks;
    }

    public void setSks(Integer sks) {
        this.sks = sks;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

}
