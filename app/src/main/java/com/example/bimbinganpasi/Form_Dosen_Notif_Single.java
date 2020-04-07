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

public class Form_Dosen_Notif_Single extends AppCompatActivity {

    @BindView(R.id.Spinner_FD_MhsList)
    Spinner spinnerMhsList;
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
        setContentView(R.layout.form_dosen_notif_single);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        initSpinnerMhsList();
        //receiveData();

        btnSend.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View view) {
                                           if (setIsi.getText().toString().equals("")){
                                               Toast.makeText(mContext, "Masukkan Isi Notifikasi", Toast.LENGTH_SHORT).show();
                                           } else {
                                               tambahNotif(no_mahasiswa,mPrefs.getUserName(),setIsi.getText().toString());
                                           }
                                       }
                                   }
        );

        spinnerMhsList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
//                requestDetailDosen(selectedName);
                if (position > 0){
                    Toast.makeText(mContext, "Anda memilih " + nama[position-1], Toast.LENGTH_SHORT).show();
                    no_mahasiswa = no_id[position-1];
                } else {
                    Toast.makeText(mContext, "Pilih mahasiswa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void initSpinnerMhsList(){
        Call<MhsBimbinganResponse> mhsBimbinganRequest = mApiService.mhsBimbinganRequest(
                String.valueOf(mPrefs.getUserID())
        );
        mhsBimbinganRequest.enqueue(new Callback<MhsBimbinganResponse>() {
            @Override
            public void onResponse(Call<MhsBimbinganResponse> call, Response<MhsBimbinganResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<Mahasiswa> list = new ArrayList<>();
                    List<String> listSpinner = new ArrayList<String>();
                    list = response.body().getMahasiswa();
                    no_id = new int[list.size()];
                    nim = new String[list.size()];
                    nama = new String[list.size()];
                    semester = new int[list.size()];
                    listSpinner.add("Pilih Mahasiswa");
                    for (int i =0;i<list.size();i++) {
                        listSpinner.add(list.get(i).getNamaMhs());
                        no_id[i] = list.get(i).getNoId();
                        nim[i] = list.get(i).getNimMhs();
                        nama[i] = list.get(i).getNamaMhs();
                        semester[i] = list.get(i).getSemester();
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            R.layout.spinner_item, listSpinner);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spinnerMhsList.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data list mahasiswa", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MhsBimbinganResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void receiveData()
//    {
//        //RECEIVE DATA VIA INTENT
//        Intent i = getIntent();
//        semester_min = i.getIntExtra("semester_min",0);
//    }

    public void tambahNotif(int user_id, String dari, String message){
        Call<MessageResponse> pushFCMSingle = mApiService.pushFCMsingle(
                user_id,
                dari,
                message);
        pushFCMSingle.enqueue(new Callback<MessageResponse>() {
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

