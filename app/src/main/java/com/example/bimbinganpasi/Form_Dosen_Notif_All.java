package com.example.bimbinganpasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bimbinganpasi.Data.Mahasiswa;
import com.example.bimbinganpasi.Data.Matkul;
import com.example.bimbinganpasi.Data.MatkulList;
import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.MhsBimbinganResponse;
import com.example.bimbinganpasi.Data.tambahMatkulResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Dosen_Notif_All extends AppCompatActivity {

    @BindView(R.id.setIsi)
    EditText setIsi;
    @BindView(R.id.btnSend)
    Button btnSend;

    int no_mahasiswa;
    String target;
    int[] no_id,semester;
    String [] nim,nama;
    Context mContext;
    BaseAPIService mApiService;
    PreferencesHelper mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dosen_notif_all);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        //receiveData();

        btnSend.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View view) {
                                           if (setIsi.getText().toString().equals("")){
                                               Toast.makeText(mContext, "Masukkan Isi Notifikasi", Toast.LENGTH_SHORT).show();
                                           } else {
                                               tambahNotif(mPrefs.getUserName(),setIsi.getText().toString());
                                           }
                                       }
                                   }
        );

    }

//    private void receiveData()
//    {
//        //RECEIVE DATA VIA INTENT
//        Intent i = getIntent();
//        semester_min = i.getIntExtra("semester_min",0);
//    }

    public void tambahNotif(String dari, String message){
        Call<MessageResponse> pushFCMmhs = mApiService.pushFCMmhs(
                dari,
                message);
        pushFCMmhs.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Notifikasi telah dikirim", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(mContext, "Gagal mengirim notifikasi", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

