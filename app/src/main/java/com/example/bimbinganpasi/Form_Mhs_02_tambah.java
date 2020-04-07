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

import com.example.bimbinganpasi.Data.Matkul;
import com.example.bimbinganpasi.Data.MatkulList;
import com.example.bimbinganpasi.Data.tambahMatkulResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_02_tambah extends AppCompatActivity {

    @BindView(R.id.Spinner_F2_MatkulList)
    Spinner spinnerMatkulList;
    @BindView(R.id.setTarget)
    EditText setTarget;
    @BindView(R.id.btnSave)
    Button btnSave;

    String target;
    int semester_min,no_matkul,semester_mhs;
    int[] no_id_matkul;
    String [] nama_matkul;
    Context mContext;
    BaseAPIService mApiService;
    PreferencesHelper mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_02_tambah);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        //receiveData();
        semester_mhs = Integer.parseInt(mPrefs.getUserSmt())+1;

        btnSave.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View view) {
                                            if (setTarget.getText().toString().equals("")){
                                                Toast.makeText(mContext, "Masukkan Target Nilai", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (setTarget.getText().toString().equalsIgnoreCase("A") ||
                                                        setTarget.getText().toString().equalsIgnoreCase("B+") ||
                                                        setTarget.getText().toString().equalsIgnoreCase("B") ||
                                                        setTarget.getText().toString().equalsIgnoreCase("C+") ||
                                                        setTarget.getText().toString().equalsIgnoreCase("C") ||
                                                        setTarget.getText().toString().equalsIgnoreCase("D+") ||
                                                        setTarget.getText().toString().equalsIgnoreCase("D") ||
                                                        setTarget.getText().toString().equalsIgnoreCase("E")) {
                                                    Form_Mhs_02.Form_Mhs_02.finish();
                                                    tambahMatkul(mPrefs.getUserID(), no_matkul, setTarget.getText().toString(), semester_mhs);
                                                } else {
                                                    Toast.makeText(mContext, "Format nilai yang anda masukkan salah", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }
        );

        initSpinnerMatkulList(semester_mhs);
        spinnerMatkulList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
//                requestDetailDosen(selectedName);
                Toast.makeText(mContext, "Kamu memilih mata kuliah " + nama_matkul[position], Toast.LENGTH_SHORT).show();
                no_matkul = no_id_matkul[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void initSpinnerMatkulList(int semester){
        Call<MatkulList> getMatkulList = mApiService.getMatkulList(
                semester);
        getMatkulList.enqueue(new Callback<MatkulList>() {
            @Override
            public void onResponse(Call<MatkulList> call, Response<MatkulList> response) {
                if (response.isSuccessful()) {
                    List<Matkul> semuaMatkulList = response.body().getMatkul();
                    List<String> listSpinner = new ArrayList<String>();
                    no_id_matkul = new int[semuaMatkulList.size()];
                    nama_matkul = new String[semuaMatkulList.size()];
                    for (int i = 0; i < semuaMatkulList.size(); i++){
                        listSpinner.add(semuaMatkulList.get(i).getNamaMatkul());
                        no_id_matkul[i] = semuaMatkulList.get(i).getNo();
                        nama_matkul[i] = semuaMatkulList.get(i).getNamaMatkul();
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            R.layout.spinner_item, listSpinner);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spinnerMatkulList.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data mata kuliah", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MatkulList> call, Throwable t) {
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

    public void tambahMatkul(int no_user_id, int no_matkul, String target, int semester){
        Call<tambahMatkulResponse> tambahMatkul = mApiService.tambahMatkulMhs(
                no_user_id,
                no_matkul,
                target,
                semester);
        tambahMatkul.enqueue(new Callback<tambahMatkulResponse>() {
            @Override
            public void onResponse(Call<tambahMatkulResponse> call, Response<tambahMatkulResponse> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(getBaseContext(),Form_Mhs_02.class);
                    startActivity(i);
                    Toast.makeText(mContext, "Mata Kuliah telah ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(mContext, "Gagal menambahkan Mata Kuliah", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<tambahMatkulResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

