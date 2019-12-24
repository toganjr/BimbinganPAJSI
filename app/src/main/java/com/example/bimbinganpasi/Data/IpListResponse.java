package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IpListResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("ip_list")
    @Expose
    private List<IpList> ipList = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<IpList> getIpList() {
        return ipList;
    }

    public void setIpList(List<IpList> ipList) {
        this.ipList = ipList;
    }

}
