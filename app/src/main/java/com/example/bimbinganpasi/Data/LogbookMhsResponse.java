package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LogbookMhsResponse {


    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("logbook_mhs")
    @Expose
    private List<Logbook_Mhs> logbookMhs = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Logbook_Mhs> getLogbookMhs() {
        return logbookMhs;
    }

    public void setLogbookMhs(List<Logbook_Mhs> logbookMhs) {
        this.logbookMhs = logbookMhs;
    }


}
