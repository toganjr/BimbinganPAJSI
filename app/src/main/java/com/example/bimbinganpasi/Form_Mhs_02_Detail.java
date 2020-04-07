package com.example.bimbinganpasi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.tambahMatkulResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_02_Detail extends AppCompatActivity {

    PreferencesHelper mPrefs;
    Context mContext;
    BaseAPIService mApiService;

    private String no_matkul,nama_matkul,sks_matkul,prasyarat_matkul,target_matkul,realisasi_matkul,nxk_matkul;
    private int semester;
    private TextView tv_nama_matkul,tv_sks_matkul,tv_prasyarat_matkul,tv_target_matkul,tv_realisasi_matkul,tv_nxk_matkul;
    private Button btn_delete,btn_tambahnilai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_02_detailmatkul);

        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);

        tv_nama_matkul = (TextView) findViewById(R.id.TVF2_detail_namamatkul);
        tv_sks_matkul = (TextView) findViewById(R.id.TVF2_detail_sksmatkul);
        tv_prasyarat_matkul = (TextView) findViewById(R.id.TVF2_detail_prasyaratmatkul);
        tv_target_matkul = (TextView) findViewById(R.id.TVF2_detail_targetmatkul);
        tv_realisasi_matkul = (TextView) findViewById(R.id.TVF2_detail_realisasimatkul);
        tv_nxk_matkul = (TextView) findViewById(R.id.TVF2_detail_nxkmatkul);

        btn_delete = (Button) findViewById(R.id.BtnF2_detail_hapusmatkul);
        btn_tambahnilai = (Button) findViewById(R.id.BtnF2_detail_tambahnilai);

        checkTypeUser();
        receiveData();

        tv_nama_matkul.setText(nama_matkul);
        tv_sks_matkul.setText(sks_matkul);
        tv_prasyarat_matkul.setText(prasyarat_matkul);
        tv_target_matkul.setText(target_matkul);
        tv_realisasi_matkul.setText(realisasi_matkul);
        tv_nxk_matkul.setText(nxk_matkul);

        rmvBtnDeleteInput();

        btn_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Delete Courses")
                        .setMessage("Are you sure you want to delete this Courses ?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                deleteMatkul(Integer.valueOf(no_matkul));
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        }
        );
        btn_tambahnilai.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),Form_Mhs_02_inputnilai.class);
                i.putExtra("no_matkul",no_matkul);
                i.putExtra("nama_matkul", nama_matkul);
                i.putExtra("semester", semester);
                i.putExtra("sks_matkul", sks_matkul);
                startActivity(i);
                finish();
            }
        }
        );
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
        semester = i.getIntExtra("semester",0);
    }

    public void deleteMatkul(int no_matkul){
        Call<MessageResponse> deleteMatkul = mApiService.deleteMatkulMhs(
                no_matkul
        );
        deleteMatkul.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    Form_Mhs_02.Form_Mhs_02.finish();
                    Intent i = new Intent(getBaseContext(),Form_Mhs_02.class);
                    startActivity(i);
                    Toast.makeText(mContext, "Mata Kuliah telah dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(mContext, "Gagal menghapus Mata Kuliah", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkTypeUser(){
        if(mPrefs.getUserType().equalsIgnoreCase("dosen")){
            btn_delete.setVisibility(View.GONE);
            btn_tambahnilai.setVisibility(View.GONE);
        }
    }

    public void rmvBtnDeleteInput(){
        if(!tv_realisasi_matkul.getText().toString().equals("")){
            btn_delete.setVisibility(View.GONE);
            btn_tambahnilai.setVisibility(View.GONE);
        }
    }

}
