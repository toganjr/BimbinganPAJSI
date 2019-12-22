package com.example.bimbinganpasi.Form_05;

public class DataNote {

    int no;
    String materi,tanggal,semester;

    public DataNote(int no, String materi, String tanggal, String semester) {
        this.no = no;
        this.materi = materi;
        this.tanggal = tanggal;
        this.semester = semester;
    }

    public int getNo() { return no;}

    public String getMateri() {
        return materi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getSemester() {
        return semester;
    }


}
