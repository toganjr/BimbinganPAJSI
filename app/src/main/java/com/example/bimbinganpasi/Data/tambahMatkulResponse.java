package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class tambahMatkulResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("error_msg")
    @Expose
    private String error_msg;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMsg() { return msg; }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError_msg() { return error_msg; }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

}
