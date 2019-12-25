package com.example.bimbinganpasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;
    Context mContext;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_bg);

        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;

        getSupportActionBar().hide();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkStatus();
            }
        }, 1500);
}

    public void checkStatus(){
        if (mPrefs.getUserisSignIn() == true){
            if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
                Intent intent = new Intent(this, Form_Mhs_Menu.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, Form_Dosen_Menu.class);
                startActivity(intent);
                finish();
            }
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        }
    }

