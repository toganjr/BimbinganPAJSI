package com.example.bimbinganpasi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bimbinganpasi.Data.Image;
import com.example.bimbinganpasi.Data.UserDataResponse;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_00 extends AppCompatActivity {
    private Button Btn_F0_Edit;
    private TextView TVF0_nama,TVF0_no_induk,TVF0_prodi,TVF0_dosen_pa,TVF0_email,
            TVF0_email2,TVF0_mobile_phone,TVF0_mobile_phone2,TVF0_alamat_mlg,TVF0_alamat_asal,
            TVF0_sma_asal,TVF0_hobby,TVF0_ekskul,TVF0_nama_ortu,TVF0_alamat_ortu,TVF0_email_ortu,TVF0_mobilephone_ortu,
            TVF0_id_fb,TVF0_id_ig,TVF0_id_line,TVF0_numb_wa;
    private RelativeLayout bio_mhs,bio_wali,bio_sosmed;
    private LinearLayout isi_mhs,isi_wali,isi_sosmed;
    private ImageView IVF0_mhs;
    private int state_mhs,state_wali,state_sosmed = 0;
    PreferencesHelper mPrefs;
    BaseAPIService mApiService;
    Context mContext;
    private List <UserDataResponse> mListUserData;
    public static Activity Form_Mhs_00;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_00);

        Form_Mhs_00 = this;
        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();

        Btn_F0_Edit = (Button) findViewById(R.id.Btn_F0_Edit);

        TVF0_nama = (TextView) findViewById(R.id.TVF0_nama);
        TVF0_no_induk = (TextView) findViewById(R.id.TVF0_no_induk);
        TVF0_prodi = (TextView) findViewById(R.id.TVF0_prodi);
        TVF0_dosen_pa = (TextView) findViewById(R.id.TVF0_dosen_pa);
        TVF0_email = (TextView) findViewById(R.id.TVF0_email);
        TVF0_email2 = (TextView) findViewById(R.id.TVF0_email2);
        TVF0_mobile_phone = (TextView) findViewById(R.id.TVF0_mobile_phone);
        TVF0_mobile_phone2 = (TextView) findViewById(R.id.TVF0_mobile_phone2);
        TVF0_alamat_mlg = (TextView) findViewById(R.id.TVF0_alamat_mlg);
        TVF0_alamat_asal = (TextView) findViewById(R.id.TVF0_alamat_asal);
        TVF0_sma_asal = (TextView) findViewById(R.id.TVF0_sma_asal);
        TVF0_hobby = (TextView) findViewById(R.id.TVF0_hobby);
        TVF0_ekskul = (TextView) findViewById(R.id.TVF0_ekskul);
        TVF0_nama_ortu = (TextView) findViewById(R.id.TVF0_nama_ortu);
        TVF0_alamat_ortu = (TextView) findViewById(R.id.TVF0_alamat_ortu);
        TVF0_email_ortu = (TextView) findViewById(R.id.TVF0_email_ortu);
        TVF0_mobilephone_ortu = (TextView) findViewById(R.id.TVF0_mobilephone_ortu);
        TVF0_id_fb = (TextView) findViewById(R.id.TVF0_id_fb);
        TVF0_id_ig = (TextView) findViewById(R.id.TVF0_id_ig);
        TVF0_id_line = (TextView) findViewById(R.id.TVF0_id_line);
        TVF0_numb_wa = (TextView) findViewById(R.id.TVF0_numb_wa);

        IVF0_mhs = (ImageView) findViewById(R.id.ImgMhs);

        bio_mhs = (RelativeLayout) findViewById(R.id.bio_mhs);
        bio_wali = (RelativeLayout) findViewById(R.id.bio_wali);
        bio_sosmed = (RelativeLayout) findViewById(R.id.bio_sosmed);
        isi_mhs = (LinearLayout) findViewById(R.id.bio_mhs_isi);
        isi_wali = (LinearLayout) findViewById(R.id.bio_wali_isi);
        isi_sosmed = (LinearLayout) findViewById(R.id.bio_sosmed_isi);

        checkUserType();

        bio_mhs.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View view) {
                                               if (state_mhs == 0) {
                                                   isi_mhs.setVisibility(View.VISIBLE);
                                                   state_mhs = 1;
                                               } else {
                                                   isi_mhs.setVisibility(View.GONE);
                                                   state_mhs = 0;
                                               }
                                           }
                                       }
        );

        bio_wali.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View view) {
                                               if (state_wali == 0) {
                                                   isi_wali.setVisibility(View.VISIBLE);
                                                   state_wali = 1;
                                               } else {
                                                   isi_wali.setVisibility(View.GONE);
                                                   state_wali = 0;
                                               }
                                           }
                                       }
        );

        bio_sosmed.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View view) {
                                               if (state_sosmed == 0) {
                                                   isi_sosmed.setVisibility(View.VISIBLE);
                                                   state_sosmed = 1;
                                               } else {
                                                   isi_sosmed.setVisibility(View.GONE);
                                                   state_sosmed = 0;
                                               }
                                           }
                                       }
        );

        Btn_F0_Edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                F0_Edit();
            }
        }
        );
        IVF0_mhs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                F0_viewPhoto();
            }
        }
        );

    }

    public void F0_Edit(){
        Intent intent = new Intent(this, Form_Mhs_00_edit.class);
        startActivity(intent);
    }

    public void F0_viewPhoto(){
        Intent intent = new Intent(this, Form_Mhs_00_Photo.class);
        startActivity(intent);
    }

    public void F0_getBiodata(String UserId){
            Call<UserDataResponse> userbiodatarequest = mApiService.userbiodatarequest(
                    UserId);
                userbiodatarequest.enqueue(new Callback<UserDataResponse>() {
                    @Override
                    public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                            String iserror = response.body().getIserror();
                            if (iserror.equals("false")) {
                                // Jika login berhasil maka data nama yang ada di response API
                                // akan diparsing ke activity selanjutnya.
                                String nama = response.body().getNama();
                                String no_induk = response.body().getNomor_induk();
                                String prodi = response.body().getProdi();
                                String dosen_pa = response.body().getDosen_pa();
                                String email = response.body().getEmail();
                                String email2 = response.body().getEmail2();
                                String mobile_phone = response.body().getMobile_phone();
                                String mobile_phone2 = response.body().getMobile_phone2();
                                String alamat_mlg = response.body().getAlamat_mlg();
                                String alamat_asal = response.body().getAlamat_asal();
                                String sma_asal = response.body().getSma_asal();
                                String hobby = response.body().getHobby();
                                String ekskul = response.body().getEkskul();
                                String nama_ortu = response.body().getNama_ortu();
                                String alamat_ortu = response.body().getAlamat_ortu();
                                String email_ortu = response.body().getEmail_ortu();
                                String mobilephone_ortu = response.body().getMobilephone_ortu();
                                String id_fb = response.body().getId_fb();
                                String id_ig = response.body().getId_ig();
                                String id_line = response.body().getId_line();
                                String numb_wa = response.body().getNumb_wa();

                                TVF0_nama.setText(nama);
                                TVF0_no_induk.setText(no_induk);
                                TVF0_prodi.setText(prodi);
                                TVF0_dosen_pa.setText(dosen_pa);
                                TVF0_email.setText(email);
                                TVF0_email2.setText(email2);
                                TVF0_mobile_phone.setText(mobile_phone);
                                TVF0_mobile_phone2.setText(mobile_phone2);
                                TVF0_alamat_mlg.setText(alamat_mlg);
                                TVF0_alamat_asal.setText(alamat_asal);
                                TVF0_sma_asal.setText(sma_asal);
                                TVF0_hobby.setText(hobby);
                                TVF0_ekskul.setText(ekskul);
                                TVF0_nama_ortu.setText(nama_ortu);
                                TVF0_alamat_ortu.setText(alamat_ortu);
                                TVF0_email_ortu.setText(email_ortu);
                                TVF0_mobilephone_ortu.setText(mobilephone_ortu);
                                TVF0_id_fb.setText(id_fb);
                                TVF0_id_ig.setText(id_ig);
                                TVF0_id_line.setText(id_line);
                                TVF0_numb_wa.setText(numb_wa);
                            }}

                        @Override
                        public void onFailure(Call<UserDataResponse> call, Throwable t){
                            Toast.makeText(mContext, "Koneksi Jaringan Bermasalah", Toast.LENGTH_SHORT).show();
                            Log.e("debug", "onFailure: ERROR > " + t.toString());
                            Intent intent = new Intent(mContext, Form_Mhs_Menu.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }

    public void F0_getPhoto(String UserId){
        Call<UserDataResponse> getPhoto = mApiService.getImage(
                    UserId);
        getPhoto.enqueue(new Callback<UserDataResponse>() {
                @Override
                public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse>response) {
                    List<Image> list = new ArrayList<>();
                    list = response.body().getImages();
                    String iserror = response.body().getIserror();

                    if (iserror.equals("false")) {
                        String[] url = new String[list.size()];
                        url[0] = list.get(0).getImage_url();
                        showPhoto(url[0]);
                    }
                }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t){
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void showPhoto(String url){
        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .transform(new CircleTransform())
                .into(IVF0_mhs);
    }

    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

    public void checkUserType(){
        if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
            F0_getBiodata(String.valueOf(mPrefs.getUserID()));
            F0_getPhoto(String.valueOf(mPrefs.getUserID()));
        } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            Btn_F0_Edit.setVisibility(View.GONE);
            F0_getBiodata(String.valueOf(mPrefs.getSelectedUserId()));
            F0_getPhoto(String.valueOf(mPrefs.getSelectedUserId()));
        }
    }
 }
