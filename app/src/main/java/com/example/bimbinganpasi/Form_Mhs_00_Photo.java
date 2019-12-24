package com.example.bimbinganpasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.bimbinganpasi.Data.Image;
import com.example.bimbinganpasi.Data.UserDataResponse;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_00_Photo extends AppCompatActivity {

    PreferencesHelper mPrefs;
    BaseAPIService mApiService;
    Context mContext;

    ImageView IVF0_mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_00_photo);

        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();

        IVF0_mhs = (ImageView) findViewById(R.id.ImgMhs);

        checkUserType();
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
                    // url[i] = userDatalist.get(i).getImages().geImage_url();
                    Log.d("Url ", String.valueOf(url[0]));
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
                .into(IVF0_mhs);
    }

    public void checkUserType(){
        if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
            F0_getPhoto(String.valueOf(mPrefs.getUserID()));
        } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            F0_getPhoto(String.valueOf(mPrefs.getSelectedUserId()));
        }
    }
}
