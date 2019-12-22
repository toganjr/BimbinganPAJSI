package com.example.bimbinganpasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bimbinganpasi.Data.UserDataResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_Menu extends AppCompatActivity {
    private TextView TV_nama,TV_no_induk;
    private Button Btn_F0,Btn_F1,Btn_F2,Btn_F3,Btn_F4,Btn_F5;
    private boolean isLogin;
    PreferencesHelper mPrefs;
    BaseAPIService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        isLogin = mPrefs.getUserisSignIn();
        super.onCreate(savedInstanceState);
            setContentView(R.layout.form_mhs_menu);

            TV_nama = (TextView) findViewById(R.id.NamaUser);
            TV_no_induk = (TextView) findViewById(R.id.NIUser);

            Btn_F0 = (Button) findViewById(R.id.Btn_F0);
            Btn_F1 = (Button) findViewById(R.id.Btn_F1);
            Btn_F2 = (Button) findViewById(R.id.Btn_F2);
            Btn_F3 = (Button) findViewById(R.id.Btn_F3);
            Btn_F4 = (Button) findViewById(R.id.Btn_F4);
            Btn_F5 = (Button) findViewById(R.id.Btn_F5);


        Btn_F0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MhsForm00();
            }

        }
        );
        Btn_F1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MhsForm01();
            }
        }
        );
        Btn_F2.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View view) { MhsForm02();
                                      }
                                  }
        );
        Btn_F3.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View view) { MhsForm03();
                                      }
                                  }
        );
        Btn_F4.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View view) { MhsForm04();
                                      }
                                  }
        );
        Btn_F5.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View view) { MhsForm05();
                                      }
                                  }
        );

    }

    @Override
    public void onResume(){
        super.onResume();
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        requestData();
    }

    public void MhsForm00(){
        Intent intent = new Intent(this, Form_Mhs_00.class);
        startActivity(intent);
    }
    public void MhsForm01(){
        Intent intent = new Intent(this, Form_Mhs_01.class);
        startActivity(intent);
    }
    public void MhsForm02(){
        Intent intent = new Intent(this, Form_Mhs_02.class);
        startActivity(intent);
    }
    public void MhsForm03(){
        Intent intent = new Intent(this, Form_Mhs_03.class);
        startActivity(intent);
    }
    public void MhsForm04(){
        Intent intent = new Intent(this, Form_Mhs_04.class);
        startActivity(intent);
    }
    public void MhsForm05(){
        Intent intent = new Intent(this, Form_Mhs_05.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbars, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.exit:
            //add the function to perform here
            mPrefs.setUserIsSignIn(false);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    public void requestData(){
        Call<UserDataResponse> loggedinuserrequest = mApiService.loggedinuserrequest(
                String.valueOf(mPrefs.getUserID()));
        loggedinuserrequest.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                String iserror_ = response.body().getIserror();
                if (iserror_.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    String nama = response.body().getNama();
                    String no_induk = response.body().getNomor_induk();
                    String semester = response.body().getSemester();
                    mPrefs.setUserSmt(semester);
                    TV_nama.setText(nama);
                    TV_no_induk.setText(no_induk);
                }
            }
                    @Override
                    public void onFailure(Call<UserDataResponse> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }



}
