package com.example.bimbinganpasi.Form_04.adapter;

public class DataNote {
    String ipkkat,ipkcap,skskat,skscap,catatan,semester;

    public DataNote(String ipkcap, String ipkkat, String skscap, String skskat,String catatan ,String semester) {
        this.ipkkat = ipkkat;
        this.ipkcap = ipkcap;
        this.skskat = skskat;
        this.skscap = skscap;
        this.catatan = catatan;
        this.semester = semester;
    }

    public String getIpkkat() {
        return ipkkat;
    }

    public String getIpkcap() {
        return ipkcap;
    }

    public String getSkskat() {
        return skskat;
    }

    public String getSkscap() {
        return skscap;
    }

    public String getCatatan() {
        return catatan;
    }

    public String getSemester() {
        return semester;
    }
}


