package com.example.bimbinganpasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.UserDataResponse;
import com.example.bimbinganpasi.Data.UserIDResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private Button BtnLogin;
    private EditText setNomorInduk,setPassword;
    Context mContext;
    BaseAPIService mApiService;
    ProgressDialog loading;
    PreferencesHelper mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_login);
        BtnLogin = (Button) findViewById(R.id.Btn_login);
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        initComponents();
    }

    public void initComponents(){

        setNomorInduk = (EditText) findViewById(R.id.LoginNI);
        setPassword = (EditText) findViewById(R.id.LoginPassword);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

    }

    public void requestLogin() {
        Call<UserIDResponse> loginRequest = mApiService.loginRequest(
                setNomorInduk.getText().toString(),
                setPassword.getText().toString());
        loginRequest.enqueue(new Callback<UserIDResponse>() {
            @Override
            public void onResponse(Call<UserIDResponse> call, Response<UserIDResponse> response) {
                loading.dismiss();
                String iserror = response.body().getIserror();
                if (iserror.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                    Integer no_user_id = response.body().getNo_user_id();
                    String utipe = response.body().getUtipe();
                    String nama = response.body().getNama();
                    mPrefs.setUserID(no_user_id);
                    mPrefs.setUserIsSignIn(true);
                    mPrefs.setUserType(utipe);
                    mPrefs.setUserName(nama);
                    if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                        mPrefs.setUserFCM(refreshedToken);
                        sendFCMKey(mPrefs.getUserID(),mPrefs.getUserFCM());
                        Intent intent = new Intent(mContext, Form_Mhs_Menu.class);
                        startActivity(intent);
                        finish();
                    } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                        mPrefs.setUserFCM(refreshedToken);
                        sendFCMKey(mPrefs.getUserID(),mPrefs.getUserFCM());
                        Intent intent = new Intent(mContext, Form_Dosen_Menu.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    /// Jika login gagal
                    String error_msg = response.body().getMsg();
                    Toast.makeText(mContext, error_msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserIDResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
            }
        });
    }

    public void sendFCMKey(int userID, String regID){
        Call<MessageResponse> sendFCMKey = mApiService.sendFCMKey(
                userID,
                regID);
        sendFCMKey.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                Boolean iserror_ = response.body().getError();
                if (iserror_.equals("false")) {
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}
