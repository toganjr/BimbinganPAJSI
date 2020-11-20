package com.example.bimbinganpasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bimbinganpasi.Data.Matkul;
import com.example.bimbinganpasi.Data.MatkulList;
import com.example.bimbinganpasi.Data.MessageResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_02_inputnilai extends AppCompatActivity {

    PreferencesHelper mPrefs;
    Context mContext;
    BaseAPIService mApiService;

    private String no_matkul,nama_matkul,nilai,sks_matkul;
    private int semester;
    private double sks;
    private TextView et_nilairealisasi;
    private Button btn_nilaisave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_02_inputnilai);

        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);

        receiveData();

        et_nilairealisasi = (TextView) findViewById(R.id.et02_nilairealisasi);
        btn_nilaisave = (Button) findViewById(R.id.btn02_nilaisave);

        btn_nilaisave.setOnClickListener(new View.OnClickListener(){
                                          @Override
                                          public void onClick(View view) {
                                              if (et_nilairealisasi.getText().toString().equalsIgnoreCase("A") ||
                                                      et_nilairealisasi.getText().toString().equalsIgnoreCase("B+") ||
                                                      et_nilairealisasi.getText().toString().equalsIgnoreCase("B") ||
                                                      et_nilairealisasi.getText().toString().equalsIgnoreCase("C+") ||
                                                      et_nilairealisasi.getText().toString().equalsIgnoreCase("C") ||
                                                      et_nilairealisasi.getText().toString().equalsIgnoreCase("D+") ||
                                                      et_nilairealisasi.getText().toString().equalsIgnoreCase("D") ||
                                                      et_nilairealisasi.getText().toString().equalsIgnoreCase("E"))
                                              {
                                                  new AlertDialog.Builder(mContext)
                                                          .setTitle("Input Nilai Realisasi")
                                                          .setMessage("Apakah anda yakin memasukkan nilai " + et_nilairealisasi.getText().toString() + " pada mata kuliah " + nama_matkul)

                                                          // Specifying a listener allows you to take an action before dismissing the dialog.
                                                          // The dialog is automatically dismissed when a dialog button is clicked.
                                                          .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                              public void onClick(DialogInterface dialog, int which) {
                                                                  // Continue with delete operation
                                                                  if(!et_nilairealisasi.getText().toString().equalsIgnoreCase("E")){
                                                                      BigDecimal nilai_pre = rubahformatnilai(et_nilairealisasi.getText().toString());
                                                                      nilai = String.valueOf(nilai_akhir(nilai_pre));
                                                                      tambahIP(String.valueOf(mPrefs.getUserID()),nilai,sks_matkul,semester);
                                                                      tambahIPK(String.valueOf(mPrefs.getUserID()),nilai,sks_matkul,semester);
                                                                      updateNilaiMatkul(no_matkul,et_nilairealisasi.getText().toString(),nilai);
                                                                  } else {
                                                                      BigDecimal nilai_pre = rubahformatnilai(et_nilairealisasi.getText().toString());
                                                                      nilai = String.valueOf(nilai_akhir(nilai_pre));
                                                                      updateNilaiMatkul(no_matkul,et_nilairealisasi.getText().toString(),nilai);
                                                                  }
                                                              }
                                                          })

                                                          // A null listener allows the button to dismiss the dialog and take no further action.
                                                          .setNegativeButton(android.R.string.no, null)
                                                          .setIcon(android.R.drawable.ic_dialog_alert)
                                                          .show();
                                              } else {
                                                  Toast.makeText(mContext, "Format nilai yang anda masukkan salah", Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }
        );
    }

    private void receiveData()
    {
        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        no_matkul = i.getStringExtra("no_matkul");
        nama_matkul = i.getStringExtra("nama_matkul");
        semester = i.getIntExtra("semester",0);
        sks_matkul = i.getStringExtra("sks_matkul");
        sks = Double.valueOf(sks_matkul);
    }

    private BigDecimal rubahformatnilai(String nilai_huruf) {
        BigDecimal nilai_angka;
        if (nilai_huruf.equalsIgnoreCase("A")){
            nilai_angka = new BigDecimal("4.0");
        } else if (nilai_huruf.equalsIgnoreCase("B+")){
            nilai_angka = new BigDecimal("3.5");
        } else if (nilai_huruf.equalsIgnoreCase("B")){
            nilai_angka = new BigDecimal("3.0");
        } else if (nilai_huruf.equalsIgnoreCase("C+")){
            nilai_angka = new BigDecimal("2.5");
        } else if (nilai_huruf.equalsIgnoreCase("C")){
            nilai_angka = new BigDecimal("2.0");
        } else if (nilai_huruf.equalsIgnoreCase("D+")){
            nilai_angka = new BigDecimal("1.5");
        } else if (nilai_huruf.equalsIgnoreCase("D")){
            nilai_angka = new BigDecimal("1.0");
        } else {
            nilai_angka = new BigDecimal("0.0");
        }
    return nilai_angka;}

    private BigDecimal nilai_akhir(BigDecimal nilai_angka){
        BigDecimal nilai_akhir;
        nilai_akhir = nilai_angka.multiply(BigDecimal.valueOf(sks));
    return nilai_akhir;}

    public void tambahIP(String no, String nilai, String sks, int semester){
        Call<MessageResponse> tambahNilaiIP = mApiService.tambahNilaiIP(
                no,
                nilai,
                sks,
                semester);
        tambahNilaiIP.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
            }}
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
            }
        });
    }

    public void tambahIPK(String no, String nilai, String sks, int semester){
        Call<MessageResponse> tambahNilaiIPK = mApiService.tambahNilaiIPK(
                no,
                nilai,
                sks,
                semester);
        tambahNilaiIPK.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    boolean iserror_ = response.body().getError();
                }}
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
            }
        });
    }

    public void updateNilaiMatkul(String no, String nilai, String nxk){
        Call<MessageResponse> updateNilaiMatkul = mApiService.updateNilaiMatkul(
                no,
                nilai,
                nxk);
        updateNilaiMatkul.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    boolean iserror_ = response.body().getError();
                    if (iserror_ == false) {
                        Toast.makeText(mContext, "Nilai berhasil diupdate", Toast.LENGTH_SHORT).show();
                        Form_Mhs_02.Form_Mhs_02.finish();
                        finish();
                        Intent i = new Intent(getBaseContext(),Form_Mhs_02.class);
                        startActivity(i);
                }}}
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


