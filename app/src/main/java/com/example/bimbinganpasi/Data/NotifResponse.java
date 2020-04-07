package com.example.bimbinganpasi.Data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("notif")
    @Expose
    private List<Notif> notif = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Notif> getNotif() {
        return notif;
    }

    public void setNotif(List<Notif> notif) {
        this.notif = notif;
    }

}