package com.example.bimbinganpasi.Data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SentNotifResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("notif")
    @Expose
    private List<NotifSent> notif = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<NotifSent> getNotifSent() {
        return notif;
    }

    public void setNotifSent(List<NotifSent> notif) {
        this.notif = notif;
    }

}