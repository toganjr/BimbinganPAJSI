package com.example.bimbinganpasi.Data;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatkulList {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("matkul")
    @Expose
    private List<Matkul> matkul = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Matkul> getMatkul() {
        return matkul;
    }

    public void setMatkul(List<Matkul> matkul) {
        this.matkul = matkul;
    }

}