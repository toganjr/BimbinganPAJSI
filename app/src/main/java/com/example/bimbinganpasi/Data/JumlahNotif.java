package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JumlahNotif {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("notif")
    @Expose
    private Integer notif;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getNotif() {
        return notif;
    }

    public void setNotif(Integer notif) {
        this.notif = notif;
    }

}