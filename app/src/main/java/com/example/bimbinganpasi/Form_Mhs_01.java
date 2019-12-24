package com.example.bimbinganpasi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class Form_Mhs_01 extends AppCompatActivity {
    Button Btn_F1_Edit1,Btn_F1_Edit2,Btn_F1_Edit3,Btn_F1_Edit0;
    PreferencesHelper mPrefs;
    BaseAPIService mApiService;
    Context mContext;
    public static Activity Form_Mhs_01;

    TextView tv01_mhs,tv01_nim,tv01_dosen,tv01_prodi,tv01_ipk,tv01_catatan,tv01_pklpendaftaran,tv01_pklpelaksanaan
            ,tv01_skripsipra,tv01_skripsipengerjaan,tv01_skripsisemhas,tv01_wisuda;
    ImageView iv01_pkl,iv01_skripsi,iv01_wisuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_01);

        Form_Mhs_01 = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;

        tv01_mhs = (TextView) findViewById(R.id.tv01_mhs);
        tv01_nim = (TextView) findViewById(R.id.tv01_nim);
        tv01_dosen = (TextView) findViewById(R.id.tv01_dosen);
        tv01_prodi = (TextView) findViewById(R.id.tv01_prodi);
        tv01_ipk = (TextView) findViewById(R.id.tv01_ipktarget);
        tv01_catatan = (TextView) findViewById(R.id.tv01_catatan);
        tv01_pklpendaftaran = (TextView) findViewById(R.id.tv01_pklpendaftaran);
        tv01_pklpelaksanaan = (TextView) findViewById(R.id.tv01_pklpelaksanaan);
        tv01_skripsipra = (TextView) findViewById(R.id.tv01_skripsipra);
        tv01_skripsipengerjaan = (TextView) findViewById(R.id.tv01_skripsipengerjaan);
        tv01_skripsisemhas = (TextView) findViewById(R.id.tv01_skripsisemhas);
        tv01_wisuda = (TextView) findViewById(R.id.tv01_wisuda);

        iv01_pkl = (ImageView) findViewById(R.id.iv01_pkl);
        iv01_skripsi = (ImageView) findViewById(R.id.iv01_skripsi);
        iv01_wisuda = (ImageView) findViewById(R.id.iv01_wisuda);

        Btn_F1_Edit0 = (Button) findViewById(R.id.Btn_F1_Edit0);
        Btn_F1_Edit1 = (Button) findViewById(R.id.Btn_F1_Edit1);
        Btn_F1_Edit2 = (Button) findViewById(R.id.Btn_F1_Edit2);
        Btn_F1_Edit3 = (Button) findViewById(R.id.Btn_F1_Edit3);

        checkUserType();

        Btn_F1_Edit0.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                            public void onClick(View view) {
                                                F1_Edit0();
                                            }
                                        }
        );
        Btn_F1_Edit1.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View view) {
                                               F1_Edit1();
                                           }
                                       }
        );
        Btn_F1_Edit2.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                            public void onClick(View view) {
                                                F1_Edit2();
                                            }
                                        }
        );
        Btn_F1_Edit3.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                            public void onClick(View view) {
                                                F1_Edit3();
                                            }
                                        }
        );
    }

    public void F1_Edit0(){
        Intent intent = new Intent(this, Form_Mhs_01_edit0.class);
        startActivity(intent);
    }
    public void F1_Edit1(){
        Intent intent = new Intent(this, Form_Mhs_01_edit1.class);
        startActivity(intent);
    }
    public void F1_Edit2(){
        Intent intent = new Intent(this, Form_Mhs_01_edit2.class);
        startActivity(intent);
    }
    public void F1_Edit3(){
        Intent intent = new Intent(this, Form_Mhs_01_edit3.class);
        startActivity(intent);
    }
    public void requestMilestoneBio(String UserId){
        Call<UserDataResponse> getMilestoneBio = mApiService.MilestoneBio(
                UserId);
        getMilestoneBio.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                String iserror_ = response.body().getIserror();
                if (iserror_.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    String mhs = response.body().getNama();
                    String nim = response.body().getNomor_induk();
                    String prodi = response.body().getProdi();
                    String dosen = response.body().getDosen_pa();
                    tv01_mhs.setText(mhs);
                    tv01_nim.setText(nim);
                    tv01_prodi.setText(prodi);
                    tv01_dosen.setText(dosen);
                }
            }
            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Jaringan Bermasalah", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Intent intent = new Intent(mContext, Form_Mhs_Menu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void requestMilestoneData(String UserId){
        Call<MilestoneDataResponse> getMilestoneData = mApiService.MilestoneData(
                UserId);
        getMilestoneData.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse> response) {
                String iserror_ = response.body().getError();
                if (iserror_.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    String ipk = response.body().getIpkTarget();
                    String catatan = response.body().getCatatanTambahan();
                    String pklpendaftaran = response.body().getPklPendaftaran();
                    String pklpelaksanaan = response.body().getPklPelaksanaan();
                    String skripsipra = response.body().getSkripsiPra();
                    String skripsipengerjaan = response.body().getSkripsiPengerjaan();
                    String skripsisemhas = response.body().getSkripsiSemhas();
                    String wisuda = response.body().getWisuda();
                    tv01_ipk.setText(ipk);
                    tv01_catatan.setText(catatan);
                    tv01_pklpendaftaran.setText(pklpendaftaran);
                    tv01_pklpelaksanaan.setText(pklpelaksanaan);
                    tv01_skripsipra.setText(skripsipra);
                    tv01_skripsipengerjaan.setText(skripsipengerjaan);
                    tv01_skripsisemhas.setText(skripsisemhas);
                    tv01_wisuda.setText(wisuda);
                }
            }
            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void F1_getPhotoPkl(String UserId){
        Call<MilestoneDataResponse> getPhotoPkl = mApiService.getImagePkl(
                UserId);
        getPhotoPkl.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse>response) {
                List<Image> list = new ArrayList<>();
                list = response.body().getImages();
                String iserror = response.body().getError();

                if (iserror.equals("false")) {
                    String[] url_pkl = new String[list.size()];
                    url_pkl[0] = list.get(0).getImage_url();
                    if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
                        if (url_pkl[0].equalsIgnoreCase("http://192.168.1.10/BimbinganPA/include//uploads/"+mPrefs.getUserID()+"_pkl.png")){
                            Log.d("Url Pkl", "true");
                            showPhotoPkl(url_pkl[0]);}
                    } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
                        if (url_pkl[0].equalsIgnoreCase("http://192.168.1.10/BimbinganPA/include//uploads/"+mPrefs.getSelectedUserId()+"_pkl.png")){
                            Log.d("Url Pkl", "true");
                            showPhotoPkl(url_pkl[0]);}
                    }
                }
            }

            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t){
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void showPhotoPkl(String url){
        Picasso.get()
                .load(url)
                .fit()
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(iv01_pkl);
    }

    public void F1_getPhotoSkripsi(String UserId){
        Call<MilestoneDataResponse> getPhotoSkripsi = mApiService.getImageSkripsi(
                UserId);
        getPhotoSkripsi.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse>response) {
                List<Image> list = new ArrayList<>();
                list = response.body().getImages();
                String iserror = response.body().getError();

                if (iserror.equals("false")) {
                    String[] url_skripsi = new String[list.size()];
                    url_skripsi[0] = list.get(0).getImage_url();
                    if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
                        if (url_skripsi[0].equalsIgnoreCase("http://192.168.1.10/BimbinganPA/include//uploads/"+mPrefs.getUserID()+"_skripsi.png")){
                            showPhotoSkripsi(url_skripsi[0]);}
                    } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
                        if (url_skripsi[0].equalsIgnoreCase("http://192.168.1.10/BimbinganPA/include//uploads/"+mPrefs.getSelectedUserId()+"_skripsi.png")){
                            showPhotoSkripsi(url_skripsi[0]);}
                    }
                }
            }

            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t){
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void showPhotoSkripsi(String url){
        Picasso.get()
                .load(url)
                .fit()
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(iv01_skripsi);
    }

    public void F1_getPhotoWisuda(String UserId){
        Call<MilestoneDataResponse> getPhotoWisuda = mApiService.getImageWisuda(
                UserId);
        getPhotoWisuda.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse>response) {
                List<Image> list = new ArrayList<>();
                list = response.body().getImages();
                String iserror = response.body().getError();

                if (iserror.equals("false")) {
                    String[] url_wisuda = new String[list.size()];
                    url_wisuda[0] = list.get(0).getImage_url();
                    if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
                        if (url_wisuda[0].equalsIgnoreCase("http://192.168.1.10/BimbinganPA/include//uploads/"+mPrefs.getUserID()+"_wisuda.png")){
                            showPhotoWisuda(url_wisuda[0]);}
                    } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
                        if (url_wisuda[0].equalsIgnoreCase("http://192.168.1.10/BimbinganPA/include//uploads/"+mPrefs.getSelectedUserId()+"_wisuda.png")){
                            showPhotoWisuda(url_wisuda[0]);}
                    }
                }
            }

            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t){
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void showPhotoWisuda(String url){
        Picasso.get()
                .load(url)
                .fit()
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(iv01_wisuda);
    }

    public void checkUserType(){
        if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
            F1_getPhotoPkl(String.valueOf(mPrefs.getUserID()));
            F1_getPhotoSkripsi(String.valueOf(mPrefs.getUserID()));
            F1_getPhotoWisuda(String.valueOf(mPrefs.getUserID()));
            requestMilestoneBio(String.valueOf(mPrefs.getUserID()));
            requestMilestoneData(String.valueOf(mPrefs.getUserID()));

        } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            Btn_F1_Edit0.setVisibility(View.GONE);
            Btn_F1_Edit1.setVisibility(View.GONE);
            Btn_F1_Edit2.setVisibility(View.GONE);
            Btn_F1_Edit3.setVisibility(View.GONE);

            F1_getPhotoPkl(String.valueOf(mPrefs.getSelectedUserId()));
            F1_getPhotoSkripsi(String.valueOf(mPrefs.getSelectedUserId()));
            F1_getPhotoWisuda(String.valueOf(mPrefs.getSelectedUserId()));
            requestMilestoneBio(String.valueOf(mPrefs.getSelectedUserId()));
            requestMilestoneData(String.valueOf(mPrefs.getSelectedUserId()));

        }
    }
}
