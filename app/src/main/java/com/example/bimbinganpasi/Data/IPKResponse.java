package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IPKResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("ipk_mhs")
    @Expose
    private List<IPKMhsResponse> ipk_mhs = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<IPKMhsResponse> getIpk_mhs() {
        return ipk_mhs;
    }

    public void setIpk_mhs(List<IPKMhsResponse> matkulMhs) {
        this.ipk_mhs = ipk_mhs;
    }

}