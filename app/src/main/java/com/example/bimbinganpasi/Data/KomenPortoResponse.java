package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KomenPortoResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("no_user_id")
    @Expose
    private Integer noUserId;
    @SerializedName("kegiatan")
    @Expose
    private String kegiatan;
    @SerializedName("komentar")
    @Expose
    private String komentar;

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

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }


}
