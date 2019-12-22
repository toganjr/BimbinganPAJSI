package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListForm4Response {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("eval_mhsf4")
    @Expose
    private List<List_Form4> evalMhsf4 = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<List_Form4> getEvalMhsf4() {
        return evalMhsf4;
    }

    public void setEvalMhsf4(List<List_Form4> evalMhsf4) {
        this.evalMhsf4 = evalMhsf4;
    }
}
