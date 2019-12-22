package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IPKMhsResponse {

    @SerializedName("ip_mhs")
    @Expose
    private String ip;
    @SerializedName("ipk_mhs")
    @Expose
    private String ipk;

    public String getIp() { return ip; }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }

}
