package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EvalMhsResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("eval_mhs")
    @Expose
    private List<Eval_Mhs> evalMhs = null;
    @SerializedName("eval_mhsf4")
    @Expose
    private List<List_Form4> evalMhsf4 = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Eval_Mhs> getEvalMhs() {
        return evalMhs;
    }

    public void setEvalMhs(List<Eval_Mhs> evalMhs) {
        this.evalMhs = evalMhs;
    }

    public List<List_Form4> getEvalMhsf4() {
        return evalMhsf4;
    }

    public void setEvalMhsf4(List<List_Form4> evalMhsf4) {
        this.evalMhsf4 = evalMhsf4;
    }
}
