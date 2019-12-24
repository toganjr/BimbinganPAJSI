package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IpList {

    @SerializedName("semester")
    @Expose
    private Integer semester;
    @SerializedName("ip")
    @Expose
    private Double ip;

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Double getIp() {
        return ip;
    }

    public void setIp(Double ip) {
        this.ip = ip;
    }


}
