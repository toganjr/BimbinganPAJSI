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

import com.example.bimbinganpasi.Data.KomenPortoResponse;
import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.Porto_Mhs;
import com.example.bimbinganpasi.Data.PortofolioMhsResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_03_tambahkomentar extends AppCompatActivity {

    @BindView(R.id.setKomentar)
    EditText setKomentar;
    @BindView(R.id.btn03SaveKomen)
    Button btnSave;

    Context mContext;
    BaseAPIService mApiService;
    PreferencesHelper mPrefs;

    int no_porto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_03_tambahkomentar);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();

        no_porto = getIntent().getIntExtra("no_porto" , 0);

        requestKomenPorto();

        btnSave.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View view) {
                                           Form_Mhs_03.Form_Mhs_03.finish();
                                           tambahKomenPorto(no_porto,setKomentar.getText().toString());
                                       }
                                   }
        );
    }

    public void requestKomenPorto(){
        Call<KomenPortoResponse> getKomenPorto = mApiService.getKomenPorto(
                String.valueOf(no_porto)
        );
        getKomenPorto.enqueue(new Callback<KomenPortoResponse>() {
            @Override
            public void onResponse(Call<KomenPortoResponse> call, Response<KomenPortoResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    String komentar = String.valueOf(response.body().getKomentar());
                    setKomentar.setText(komentar);
                }
            }
            @Override
            public void onFailure(Call<KomenPortoResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void tambahKomenPorto(int no_porto, String komentar){
        Call<MessageResponse> tambahKomenPorto = mApiService.tambahKomenPorto(
                no_porto,
                komentar);
        tambahKomenPorto.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(getBaseContext(),Form_Mhs_03.class);
                    startActivity(i);
                    Toast.makeText(mContext, "Komentar telah ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(mContext, "Gagal menambahkan Komentar", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
