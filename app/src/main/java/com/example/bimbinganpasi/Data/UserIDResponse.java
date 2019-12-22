package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class UserIDResponse {

    @SerializedName("error")
    @Expose
    String iserror;
    @SerializedName("error_msg")
    @Expose
    String error_msg;
    @SerializedName("no_user_id")
    @Expose
    Integer no_user_id;
    @SerializedName("nama")
    @Expose
    String nama;
    @SerializedName("nomor_induk")
    @Expose
    String nomor_induk;
    @SerializedName("utipe")
    @Expose
    String utipe;

    public String getIserror() { return iserror; }
    public void setIserror(String iserror) { this.iserror = iserror; }

    public String getMsg() { return error_msg; }
    public void setMsg(String error_msg) { this.error_msg = error_msg; }

    public Integer getNo_user_id() { return no_user_id;}
    public void setNo_user_id(Integer no_user_id) { this.no_user_id = no_user_id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNo_induk() { return nomor_induk; }
    public void setNo_induk(String nomor_induk) { this.nomor_induk = nomor_induk; }

    public String getUtipe() { return utipe; }
    public void setUtipe(String utipe) { this.utipe = utipe; }
}
