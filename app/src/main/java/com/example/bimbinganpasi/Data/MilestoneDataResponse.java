package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MilestoneDataResponse {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("ipk_target")
    @Expose
    private String ipkTarget;
    @SerializedName("catatan_tambahan")
    @Expose
    private String catatanTambahan;
    @SerializedName("pkl_pendaftaran")
    @Expose
    private String pklPendaftaran;
    @SerializedName("pkl_pelaksanaan")
    @Expose
    private String pklPelaksanaan;
    @SerializedName("skripsi_pra")
    @Expose
    private String skripsiPra;
    @SerializedName("skripsi_pengerjaan")
    @Expose
    private String skripsiPengerjaan;
    @SerializedName("skripsi_semhas")
    @Expose
    private String skripsiSemhas;
    @SerializedName("wisuda")
    @Expose
    private String wisuda;
    @SerializedName("images")
    @Expose
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getIpkTarget() {
        return ipkTarget;
    }

    public void setIpkTarget(String ipkTarget) {
        this.ipkTarget = ipkTarget;
    }

    public String getCatatanTambahan() {
        return catatanTambahan;
    }

    public void setCatatanTambahan(String catatanTambahan) {
        this.catatanTambahan = catatanTambahan;
    }

    public String getPklPendaftaran() {
        return pklPendaftaran;
    }

    public void setPklPendaftaran(String pklPendaftaran) {
        this.pklPendaftaran = pklPendaftaran;
    }

    public String getPklPelaksanaan() {
        return pklPelaksanaan;
    }

    public void setPklPelaksanaan(String pklPelaksanaan) {
        this.pklPelaksanaan = pklPelaksanaan;
    }

    public String getSkripsiPra() {
        return skripsiPra;
    }

    public void setSkripsiPra(String skripsiPra) {
        this.skripsiPra = skripsiPra;
    }

    public String getSkripsiPengerjaan() {
        return skripsiPengerjaan;
    }

    public void setSkripsiPengerjaan(String skripsiPengerjaan) {
        this.skripsiPengerjaan = skripsiPengerjaan;
    }

    public String getSkripsiSemhas() {
        return skripsiSemhas;
    }

    public void setSkripsiSemhas(String skripsiSemhas) {
        this.skripsiSemhas = skripsiSemhas;
    }

    public String getWisuda() {
        return wisuda;
    }

    public void setWisuda(String wisuda) {
        this.wisuda = wisuda;
    }

    public List<Image> getImages() {
        return images;
    }

}
