package com.example.bimbinganpasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bimbinganpasi.Data.Image;
import com.example.bimbinganpasi.Data.MilestoneDataResponse;
import com.example.bimbinganpasi.Data.UserDataResponse;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_01_edit0 extends AppCompatActivity {

    EditText editText1,editText2;
    Button btnSave;

    PreferencesHelper mPrefs;
    BaseAPIService mApiService;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_01_edit0);

        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();

        editText1 = (EditText) findViewById(R.id.et01_edit0ipk);
        editText2 = (EditText) findViewById(R.id.et01_edit0catatan);
        btnSave = (Button) findViewById(R.id.btn01_edit0save);

        requestMilestoneData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editMilestoneData();
            }
        });
    }

    public void requestMilestoneData(){
        Call<MilestoneDataResponse> getMilestoneData = mApiService.MilestoneData(
                String.valueOf(mPrefs.getUserID()));
        getMilestoneData.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse> response) {
                String iserror_ = response.body().getError();
                if (iserror_.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    String ipk = response.body().getIpkTarget();
                    String catatan = response.body().getCatatanTambahan();
                    editText1.setText(ipk);
                    editText2.setText(catatan);
                }
            }
            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void editMilestoneData(){
        Form_Mhs_01.Form_Mhs_01.finish();
        Call<MilestoneDataResponse> milestoneedit00 = mApiService.edit00Milestone(
                String.valueOf(mPrefs.getUserID()),
                editText1.getText().toString(),
                editText2.getText().toString()
        );
        milestoneedit00.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse> response) {
                String iserror = response.body().getError();
                if (iserror.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    Toast.makeText(mContext, "EDIT BERHASIL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Form_Mhs_01.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(mContext, "EDIT GAGAL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Form_Mhs_01.class);
                    startActivity(intent);
                    finish();
                }}

            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t){
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Form_Mhs_Menu.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
