package com.example.bimbinganpasi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bimbinganpasi.Data.MessageResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_05_tambah extends AppCompatActivity {


    EditText setMateriKonsul,setTanggalKonsul;
    Button btnSave;

    private Calendar dateTime = Calendar.getInstance();
    private DatePickerDialog mTanggalKonsul;
    private String tanggalKonsul;

    Context mContext;
    BaseAPIService mApiService;
    PreferencesHelper mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_05_tambah);

        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();

        setMateriKonsul = (EditText) findViewById(R.id.setMateriKonsul);
        setTanggalKonsul = (EditText) findViewById(R.id.setTanggalKonsul);
        setTanggalKonsul.setInputType(InputType.TYPE_NULL);
        btnSave = (Button) findViewById(R.id.btn05Save);

        setTanggalKonsul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTanggalKonsul.show();
            }
        });

        initDateTimePickerDialog();

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (setMateriKonsul.getText().toString().equals("")) {
                    Toast.makeText(mContext, "Masukkan Materi Konsultasi", Toast.LENGTH_SHORT).show();
                } else if (setTanggalKonsul.getText().toString().equals("")){
                    Toast.makeText(mContext, "Masukkan Tanggal Konsultasi", Toast.LENGTH_SHORT).show();
                } else {
                    Form_Mhs_05.Form_Mhs_05.finish();
                    tambahLogbook(mPrefs.getSelectedUserId(), setMateriKonsul.getText().toString(), tanggalKonsul, mPrefs.getUserSmt());
                }
            }
        } );

    }

    public void tambahLogbook(int no_user_id, String materi_konsul, String tanggal_konsul, String semester){
        Call<MessageResponse> tambahLogbook = mApiService.tambahLogbook(
                no_user_id,
                materi_konsul,
                tanggal_konsul,
                semester);
        tambahLogbook.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(getBaseContext(),Form_Mhs_05.class);
                    startActivity(i);
                    Toast.makeText(mContext, "Logbook telah ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(mContext, "Gagal menambahkan Logbook", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initDateTimePickerDialog(){
        final SimpleDateFormat dateFormatterText = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        final SimpleDateFormat jsonFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        mTanggalKonsul = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateTime.set(year, monthOfYear, dayOfMonth);
                tanggalKonsul = jsonFormat.format(dateTime.getTime());
                setTanggalKonsul.setText(dateFormatterText.format(dateTime.getTime()));
            }

        },  dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH));
    }
}
