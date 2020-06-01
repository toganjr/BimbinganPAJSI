package com.example.bimbinganpasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bimbinganpasi.Data.Image;
import com.example.bimbinganpasi.Data.JumlahNotif;
import com.example.bimbinganpasi.Data.Mahasiswa;
import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.MhsBimbinganResponse;
import com.example.bimbinganpasi.Data.UserDataResponse;
import com.example.bimbinganpasi.Form_Menu.Adapter.DataNote;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_Menu extends AppCompatActivity {
    private TextView TV_nama,TV_no_induk,TV_peringatan,TV_peringatandosen;
    private Button Btn_F0,Btn_F1,Btn_F2,Btn_F3,Btn_F4,Btn_F5;
    private ImageView IV_mhs;
    private boolean isLogin;
    public static Activity Form_Mhs_Menu;
    private boolean isStarted = false;
    PreferencesHelper mPrefs;
    BaseAPIService mApiService;
    Context mContext;
    Menu myMenu;

    int no_id,semester,newNotif;
    String nama,nim,kat_sks,kat_ipk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        isLogin = mPrefs.getUserisSignIn();
        super.onCreate(savedInstanceState);
        mContext = this;
        Form_Mhs_Menu = this;
        setContentView(R.layout.form_mhs_menu);


            IV_mhs = (ImageView) findViewById(R.id.ImageUser);
            TV_nama = (TextView) findViewById(R.id.NamaUser);
            TV_no_induk = (TextView) findViewById(R.id.NIUser);
            TV_peringatan = (TextView) findViewById(R.id.peringatan_F0);
            TV_peringatandosen = (TextView) findViewById(R.id.peringatandosen_F0);

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
        invalidateOptionsMenu();
        checkUserType();
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
        if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            menu.findItem( R.id.notif ).setVisible( false );
            menu.findItem( R.id.chgpassword ).setVisible( false );
            menu.findItem( R.id.exit ).setVisible( false );
        } else {
            requestNotif(new ApiCallback(){
                @Override
                public void onSuccess(Integer result){
                    newNotif = result;
                    MenuItem item = menu.findItem(R.id.notif);
                    LayerDrawable icon = (LayerDrawable) item.getIcon();
                    Utils2.setBadgeCount(mContext, icon, newNotif);
                }
            });
            // Update LayerDrawable's BadgeDrawable
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            menu.findItem( R.id.chgpassword ).setVisible( false );
            menu.findItem( R.id.exit ).setVisible( false );
        } else {
            requestNotif(new ApiCallback(){
                @Override
                public void onSuccess(Integer result){
                    newNotif = result;

                        MenuItem item = menu.findItem(R.id.notif);
                        LayerDrawable icon = (LayerDrawable) item.getIcon();
                        Utils2.setBadgeCount(mContext, icon, newNotif);

                }
            });
            // Update LayerDrawable's BadgeDrawable
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.exit:
            //add the function to perform here
            mPrefs.setUserIsSignIn(false);
            delFCMKey(mPrefs.getUserID(),mPrefs.getUserFCM());
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return(true);
        case R.id.chgpassword:
            Intent intent2 = new Intent(this, Form_Change_Password.class);
            startActivity(intent2);
            return(true);
        case R.id.notif:
            Intent intent3 = new Intent(this, Form_User_Notif.class);
            startActivity(intent3);
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }


    public void requestData(String userID){
        Call<UserDataResponse> loggedinuserrequest = mApiService.loggedinuserrequest(
                userID);
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
                    if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")) {
                        String no_dosen_id = response.body().getNo_dosen_id();
                        mPrefs.setDosenNo(no_dosen_id);
                    } else{

                    }
                }
            }
                    @Override
                    public void onFailure(Call<UserDataResponse> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }

    public void requestNotif(final ApiCallback callback){
        Call<JumlahNotif> call = mApiService.getNewNotif(
                mPrefs.getUserID()
        );
        call.enqueue(new Callback<JumlahNotif>() {
            // If success
            @Override
            public void onResponse(Call<JumlahNotif>call, Response<JumlahNotif> response) {
                Integer list = response.body().getNotif();
                callback.onSuccess(list); // pass the list
            }
            // If failed
            @Override
            public void onFailure(Call<JumlahNotif>call, Throwable t) {
                // Log error here since request failed
            }
        });
    }

    public interface ApiCallback{
        void onSuccess(Integer result);
    }

    public void delFCMKey(int userID, String regID){
        Call<MessageResponse> delFCMKey = mApiService.delFCMKey(
                userID,
                regID);
        delFCMKey.enqueue(new Callback<MessageResponse>() {
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


    public void checkMhsKat(String UserId){
        Call<MhsBimbinganResponse> checkMhsKat = mApiService.checkMhsKat(
                UserId
        );
        checkMhsKat.enqueue(new Callback<MhsBimbinganResponse>() {
            @Override
            public void onResponse(Call<MhsBimbinganResponse> call, Response<MhsBimbinganResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<Mahasiswa> list = new ArrayList<>();
                    list = response.body().getMahasiswa();
                    kat_sks = list.get(0).getKategoriSks();
                    kat_ipk = list.get(0).getKategoriIpk();
                    if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")) {
                            if("kritis".equalsIgnoreCase(kat_sks) || "kurang".equalsIgnoreCase(kat_sks) || "kritis".equalsIgnoreCase(kat_ipk) || "kurang".equalsIgnoreCase(kat_ipk)){
                                TV_peringatan.setVisibility(View.VISIBLE);
                        }
                    } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")) {
                            if("kritis".equalsIgnoreCase(kat_sks) || "kurang".equalsIgnoreCase(kat_sks) || "kritis".equalsIgnoreCase(kat_ipk) || "kurang".equalsIgnoreCase(kat_ipk)){
                                TV_peringatandosen.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<MhsBimbinganResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Jaringan Bermasalah", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void getPhoto(String UserId){
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
                    // url[i] = userDatalist.get(i).getImages().geImage_url();
                    Log.d("Url ", String.valueOf(url[0]));
                    showPhoto(url[0]);
                }
            }
//                    Glide.with(mContext).load(url).into(IVF0_mhs);
//                    }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t){
                Log.d("url TEST 2 ", "FAIL");
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
                .transform(new Form_Mhs_Menu.CircleTransform())
                .into(IV_mhs);
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
            requestData(String.valueOf(mPrefs.getUserID()));
            checkMhsKat(String.valueOf(mPrefs.getUserID()));
            getPhoto(String.valueOf(mPrefs.getUserID()));
        } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            requestData(String.valueOf(mPrefs.getSelectedUserId()));
            checkMhsKat(String.valueOf(mPrefs.getSelectedUserId()));
            getPhoto(String.valueOf(mPrefs.getSelectedUserId()));
        }
    }


}
