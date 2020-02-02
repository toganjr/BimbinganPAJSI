package com.example.bimbinganpasi.Form_05;

public class DataNote {

    int no,status;
    String materi,tanggal,semester;

    public DataNote(int no, String materi, String tanggal, String semester, int status) {
        this.no = no;
        this.materi = materi;
        this.tanggal = tanggal;
        this.semester = semester;
        this.status = status;
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

    public int getStatus() { return status;}

}
