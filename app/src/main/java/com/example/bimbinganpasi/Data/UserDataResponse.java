package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class UserDataResponse implements Serializable {

    @SerializedName("error")
    @Expose
    private String iserror;
    @SerializedName("error_msg")
    @Expose
    private String error_msg;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nomor_induk")
    @Expose
    private String nomor_induk;
    @SerializedName("prodi")
    @Expose
    private String prodi;
    @SerializedName("dosen_pa")
    @Expose
    private String dosen_pa;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email2")
    @Expose
    private String email2;
    @SerializedName("mobile_phone")
    @Expose
    private String mobile_phone;
    @SerializedName("mobile_phone2")
    @Expose
    private String mobile_phone2;
    @SerializedName("alamat_mlg")
    @Expose
    private String alamat_mlg;
    @SerializedName("alamat_asal")
    @Expose
    private String alamat_asal;
    @SerializedName("sma_asal")
    @Expose
    private String sma_asal;
    @SerializedName("hobby")
    @Expose
    private String hobby;
    @SerializedName("ekskul")
    @Expose
    private String ekskul;
    @SerializedName("nama_ortu")
    @Expose
    private String nama_ortu;
    @SerializedName("alamat_ortu")
    @Expose
    private String alamat_ortu;
    @SerializedName("email_ortu")
    @Expose
    private String email_ortu;
    @SerializedName("mobilephone_ortu")
    @Expose
    private String mobilephone_ortu;
    @SerializedName("id_fb")
    @Expose
    private String id_fb;
    @SerializedName("id_ig")
    @Expose
    private String id_ig;
    @SerializedName("id_line")
    @Expose
    private String id_line;
    @SerializedName("numb_wa")
    @Expose
    private String numb_wa;
    @SerializedName("images")
    @Expose
    private List<Image> images;

    public String getIserror() {
        return iserror;
    }
    public void setIserror(String iserror) {
        this.iserror = iserror;
    }
    public String getMsg() {
        return error_msg;
    }
    public void setMsg(String error_msg) {
        this.error_msg = error_msg;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNomor_induk() {
        return nomor_induk;
    }
    public void setNomor_induk(String nomor_induk) {
        this.nomor_induk = nomor_induk;
    }
    public String getProdi() {
        return prodi;
    }
    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
    public String getDosen_pa() {
        return dosen_pa;
    }
    public void setDosen_pa(String dosen_pa) {
        this.dosen_pa = dosen_pa;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail2() {
        return email2;
    }
    public void setEmail2(String email2) {
        this.email2 = email2;
    }
    public String getMobile_phone() {
        return mobile_phone;
    }
    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }
    public String getMobile_phone2() {
        return mobile_phone2;
    }
    public void setMobile_phone2(String mobile_phone2) {
        this.mobile_phone2 = mobile_phone2;
    }
    public String getAlamat_mlg() {
        return alamat_mlg;
    }
    public void setAlamat_mlg(String alamat_mlg) {
        this.alamat_mlg = alamat_mlg;
    }
    public String getAlamat_asal() {
        return alamat_asal;
    }
    public void setAlamat_asal(String alamat_asal) {
        this.alamat_asal = alamat_asal;
    }
    public String getSma_asal() {
        return sma_asal;
    }
    public void setSma_asal(String sma_asal) {
        this.sma_asal = sma_asal;
    }
    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public String getEkskul() {
        return ekskul;
    }
    public void setEkskul(String ekskul) {
        this.ekskul = ekskul;
    }

    public String getNama_ortu() {
        return nama_ortu;
    }
    public void setNama_ortu(String nama_ortu) {
        this.nama_ortu = nama_ortu;
    }
    public String getAlamat_ortu() {
        return alamat_ortu;
    }
    public void setAlamat_ortu(String alamat_ortu) {
        this.alamat_ortu = alamat_ortu;
    }
    public String getEmail_ortu() {
        return email_ortu;
    }
    public void setEmail_ortu(String email_ortu) {
        this.email_ortu = email_ortu;
    }
    public String getMobilephone_ortu() {
        return mobilephone_ortu;
    }
    public void setMobilephone_ortu(String mobilephone_ortu) {
        this.mobilephone_ortu = mobilephone_ortu;
    }
    public String getId_fb() {
        return id_fb;
    }
    public void setId_fb(String id_fb) {
        this.id_fb = id_fb;
    }
    public String getId_ig() {
        return id_ig;
    }
    public void setId_ig(String id_ig) {
        this.id_ig = id_ig;
    }
    public String getId_line() {
        return id_line;
    }
    public void setId_line(String id_line) {
        this.id_line = id_line;
    }
    public String getNumb_wa() {
        return numb_wa;
    }
    public void setNumb_wa(String numb_wa) {
        this.numb_wa = numb_wa;
    }

    public List<Image> getImages() {
        return images;
    }
}
