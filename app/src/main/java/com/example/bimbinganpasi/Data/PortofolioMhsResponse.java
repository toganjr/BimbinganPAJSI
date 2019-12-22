package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PortofolioMhsResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("porto_mhs")
    @Expose
    private List<Porto_Mhs> portoMhs = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Porto_Mhs> getPortoMhs() {
        return portoMhs;
    }

    public void setPortoMhs(List<Porto_Mhs> portoMhs) {
        this.portoMhs = portoMhs;
    }

}
