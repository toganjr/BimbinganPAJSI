package com.example.bimbinganpasi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Form_Mhs_02_Detail extends AppCompatActivity {

    PreferencesHelper mPrefs;

    private String no_matkul,nama_matkul,sks_matkul,prasyarat_matkul,target_matkul,realisasi_matkul,nxk_matkul;
    private TextView tv_nama_matkul,tv_sks_matkul,tv_prasyarat_matkul,tv_target_matkul,tv_realisasi_matkul,tv_nxk_matkul;
    private Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_02_detailmatkul);

        mPrefs = ((BimbPA) getApplication()).getPrefs();

        tv_nama_matkul = (TextView) findViewById(R.id.TVF2_detail_namamatkul);
        tv_sks_matkul = (TextView) findViewById(R.id.TVF2_detail_sksmatkul);
        tv_prasyarat_matkul = (TextView) findViewById(R.id.TVF2_detail_prasyaratmatkul);
        tv_target_matkul = (TextView) findViewById(R.id.TVF2_detail_targetmatkul);
        tv_realisasi_matkul = (TextView) findViewById(R.id.TVF2_detail_realisasimatkul);
        tv_nxk_matkul = (TextView) findViewById(R.id.TVF2_detail_nxkmatkul);

        btn_delete = (Button) findViewById(R.id.BtnF2_detail_hapusmatkul);

        checkType();
        receiveData();

        tv_nama_matkul.setText(nama_matkul);
        tv_sks_matkul.setText(sks_matkul);
        tv_prasyarat_matkul.setText(prasyarat_matkul);
        tv_target_matkul.setText(target_matkul);
        tv_realisasi_matkul.setText(realisasi_matkul);
        tv_nxk_matkul.setText(nxk_matkul);
    }

    private void receiveData()
    {
        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        no_matkul = i.getStringExtra("no");
        nama_matkul = i.getStringExtra("nama_matkul");
        sks_matkul = i.getStringExtra("sks_matkul");
        prasyarat_matkul = i.getStringExtra("prasyarat_matkul");
        target_matkul = i.getStringExtra("target_matkul");
        realisasi_matkul = i.getStringExtra("realisasi_matkul");
        nxk_matkul = i.getStringExtra("nxk_matkul");
    }

    public void checkType(){
        if(mPrefs.getUserType().equalsIgnoreCase("dosen")){
            btn_delete.setVisibility(View.GONE);
        }

}}
