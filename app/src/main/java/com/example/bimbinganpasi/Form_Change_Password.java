package com.example.bimbinganpasi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bimbinganpasi.Data.Mahasiswa;
import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.MhsBimbinganResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Change_Password extends AppCompatActivity {

    EditText set_oldpassword,set_newpassword,set_newpassword2;
    Button btn_chpassword;

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_change_password);

        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;

        set_oldpassword = (EditText) findViewById(R.id.et_oldpassword);
        set_newpassword = (EditText) findViewById(R.id.et_newpassword);
        set_newpassword2 = (EditText) findViewById(R.id.et_newpassword2);

        btn_chpassword = (Button) findViewById(R.id.btn_chpassword);

        btn_chpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (set_oldpassword.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(mContext, "Masukkan Password", Toast.LENGTH_SHORT).show();
                } else if (set_newpassword.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(mContext, "Masukkan Password Baru", Toast.LENGTH_SHORT).show();
                } else if (!set_newpassword.getText().toString().equals(set_newpassword2.getText().toString())){
                    Toast.makeText(mContext, "Password baru anda tidak cocok", Toast.LENGTH_SHORT).show();
                } else {
                    checkUserType();
                }
            }
        });
    }

    public void changePassword(String UserId, String password, String newpassword){
        Call<MessageResponse> changePassword = mApiService.changePassword(
                UserId,
                password,
                newpassword
        );
        changePassword.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    Toast.makeText(mContext, "Password berhasil diupdate", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(mContext, "Password yang anda masukkan salah", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Jaringan Bermasalah", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void checkUserType(){
        if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
            changePassword(String.valueOf(mPrefs.getUserID()),
                    set_oldpassword.getText().toString(),
                    set_newpassword.getText().toString());
        } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            changePassword(String.valueOf(mPrefs.getUserID()),
                    set_oldpassword.getText().toString(),
                    set_newpassword.getText().toString());
        }
    }
}
