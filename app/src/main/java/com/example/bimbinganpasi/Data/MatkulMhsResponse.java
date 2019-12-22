package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatkulMhsResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("matkul_mhs")
    @Expose
    private List<Matkul_Mhs> matkulMhs = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Matkul_Mhs> getMatkulMhs() {
        return matkulMhs;
    }

    public void setMatkulMhs(List<Matkul_Mhs> matkulMhs) {
        this.matkulMhs = matkulMhs;
    }

}

