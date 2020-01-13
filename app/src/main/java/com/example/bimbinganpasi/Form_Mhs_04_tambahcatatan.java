package com.example.bimbinganpasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bimbinganpasi.Data.CatatanPAResponse;
import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.tambahMatkulResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_04_tambahcatatan extends AppCompatActivity {

    @BindView(R.id.set04Catatan)
    EditText setCatatan;
    @BindView(R.id.btn04Savectt)
    Button btnSave;

    Context mContext;
    BaseAPIService mApiService;
    PreferencesHelper mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_04_tambahcatatan);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();

        requestCatatanData();

        btnSave.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View view) {
                                           Form_Mhs_04.Form_Mhs_04.finish();
                                           tambahCatatanPA(mPrefs.getSelectedUserId(),setCatatan.getText().toString(),Integer.parseInt(mPrefs.getUserSmt())-1);
                                       }
                                   }
        );
    }

    public void requestCatatanData(){
        Call<CatatanPAResponse> getCatatanPAF4 = mApiService.getCatatanPAF4(
                String.valueOf(mPrefs.getSelectedUserId()),
                Integer.parseInt(mPrefs.getUserSmt())-1
        );
        getCatatanPAF4.enqueue(new Callback<CatatanPAResponse>() {
            @Override
            public void onResponse(Call<CatatanPAResponse> call, Response<CatatanPAResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    String catatanPA = String.valueOf(response.body().getCatatan());
                    setCatatan.setText(catatanPA);
                }
            }
            @Override
            public void onFailure(Call<CatatanPAResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void tambahCatatanPA(int no_user_id, String catatan, int semester){
        Call<MessageResponse> tambahCatatanPAF4 = mApiService.tambahCatatanPAF4(
                no_user_id,
                catatan,
                semester);
        tambahCatatanPAF4.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(getBaseContext(),Form_Mhs_04.class);
                    startActivity(i);
                    Toast.makeText(mContext, "Catatan telah ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(mContext, "Gagal menambahkan Catatan", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
