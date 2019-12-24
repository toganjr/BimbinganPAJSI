package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatatanPAResponse {


    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("no_user_id")
    @Expose
    private Integer noUserId;
    @SerializedName("catatan")
    @Expose
    private String catatan;
    @SerializedName("semester")
    @Expose
    private Integer semester;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getNoUserId() {
        return noUserId;
    }

    public void setNoUserId(Integer noUserId) {
        this.noUserId = noUserId;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

}
