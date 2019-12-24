package com.example.bimbinganpasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.tambahMatkulResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_03_tambah extends AppCompatActivity {

    @BindView(R.id.setKegiatan)
    EditText setKegiatan;
    @BindView(R.id.setKeterangan)
    EditText setKeterangan;
    @BindView(R.id.setKategori)
    EditText setKategori;
    @BindView(R.id.setSemester)
    EditText setSemester;
    @BindView(R.id.btn03Save)
    Button btnSave;

    Context mContext;
    BaseAPIService mApiService;
    PreferencesHelper mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_03_tambah);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (setKegiatan.getText().toString().equals("")) {
                    Toast.makeText(mContext, "Masukkan Kegiatan", Toast.LENGTH_SHORT).show();
                } else if (setKeterangan.getText().toString().equals("")){
                    Toast.makeText(mContext, "Masukkan Keterangan", Toast.LENGTH_SHORT).show();
                } else if (setKategori.getText().toString().equals("")){
                    Toast.makeText(mContext, "Masukkan Kategori", Toast.LENGTH_SHORT).show();
                } else if (setSemester.getText().toString().equals("")){
                    Toast.makeText(mContext, "Masukkan Semester", Toast.LENGTH_SHORT).show();
                } else {
                    Form_Mhs_03.Form_Mhs_03.finish();
                    tambahPortofolio(mPrefs.getUserID(), setKegiatan.getText().toString(), setKeterangan.getText().toString(), setKategori.getText().toString(), setSemester.getText().toString());
                }
            }
        } );
    }

    public void tambahPortofolio(int no_user_id, String kegiatan, String keterangan, String kategori, String semester){
        Call<MessageResponse> tambahPortofolio = mApiService.tambahPortofolio(
                no_user_id,
                kegiatan,
                keterangan,
                kategori,
                semester);
        tambahPortofolio.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(getBaseContext(),Form_Mhs_03.class);
                    startActivity(i);
                    Toast.makeText(mContext, "Portofolio telah ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(mContext, "Gagal menambahkan Portofolio", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
