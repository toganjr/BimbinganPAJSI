package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatatanEvalResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("min_sks")
    @Expose
    private Integer minSks;
    @SerializedName("normal_sks")
    @Expose
    private Integer normalSKS;
    @SerializedName("baik_sks")
    @Expose
    private Integer baikSks;
    @SerializedName("semester")
    @Expose
    private Integer semester;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getMinSks() {
        return minSks;
    }

    public void setMinSks(Integer minSks) {
        this.minSks = minSks;
    }

    public Integer getNormalSKS() {
        return normalSKS;
    }

    public void setNormalSKS(Integer normalSKS) {
        this.normalSKS = normalSKS;
    }

    public Integer getBaikSks() {
        return baikSks;
    }

    public void setBaikSks(Integer baikSks) {
        this.baikSks = baikSks;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

}
