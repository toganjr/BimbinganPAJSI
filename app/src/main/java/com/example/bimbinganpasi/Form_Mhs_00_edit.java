package com.example.bimbinganpasi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bimbinganpasi.Data.Image;
import com.example.bimbinganpasi.Data.UserDataResponse;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_00_edit extends AppCompatActivity {

    PreferencesHelper mPrefs;
    BaseAPIService mApiService;
    Context mContext;


    private EditText set__email,set__email2,set__mobile_phone,set__mobile_phone2,set__alamat_mlg,set__alamat_asal,
            set__sma_asal,set__hobby,set__ekskul,set__nama_ortu,set__alamat_ortu,set__email_ortu,set__mobilephone_ortu,
            set__id_fb,set__id_ig,set__id_line,set__numb_wa;
    private Button BtnF0Save;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;
    private Button Button_F0_PhotoUp,Button_F0_Save;
    private ImageView IVF0_mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_00_edit);

        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();

        set__email = (EditText) findViewById(R.id.setEmail);
        set__email2 = (EditText) findViewById(R.id.setEmail2);
        set__mobile_phone = (EditText) findViewById(R.id.setMobilephone);
        set__mobile_phone2 = (EditText) findViewById(R.id.setMobilephone2);
        set__alamat_mlg = (EditText) findViewById(R.id.setAlamatmlg);
        set__alamat_asal = (EditText) findViewById(R.id.setAlamatasal);
        set__sma_asal = (EditText) findViewById(R.id.setSmaasal);
        set__hobby = (EditText) findViewById(R.id.setHobby);
        set__ekskul = (EditText) findViewById(R.id.setEkskul);
        set__nama_ortu = (EditText) findViewById(R.id.setNamaortu);
        set__alamat_ortu = (EditText) findViewById(R.id.setAlamatortu);
        set__email_ortu = (EditText) findViewById(R.id.setEmailortu);
        set__mobilephone_ortu = (EditText) findViewById(R.id.setMobilephoneortu);
        set__id_fb = (EditText) findViewById(R.id.setFbid);
        set__id_ig = (EditText) findViewById(R.id.setIgid);
        set__id_line = (EditText) findViewById(R.id.setLineid);
        set__numb_wa = (EditText) findViewById(R.id.setNumbwa);
        IVF0_mhs = (ImageView) findViewById(R.id.ImgMhs);
        Button_F0_PhotoUp = (Button) findViewById(R.id.Btn_F0_PhotoUp);
        Button_F0_Save = (Button) findViewById(R.id.BtnF0Save);

        F0_getBiodata();
        F0_getPhoto();

        Button_F0_PhotoUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelectImage();
            }
        });

        Button_F0_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                F0_updateBiodata();
            }
        });
    }

    public void F0_getBiodata(){
        Call<UserDataResponse> userbiodatarequest = mApiService.userbiodatarequest(
                String.valueOf(mPrefs.getUserID()));
        userbiodatarequest.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                String iserror = response.body().getIserror();
                if (iserror.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
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

                    set__email.setText(email);
                    set__email2.setText(email2);
                    set__mobile_phone.setText(mobile_phone);
                    set__mobile_phone2.setText(mobile_phone2);
                    set__alamat_mlg.setText(alamat_mlg);
                    set__alamat_asal.setText(alamat_asal);
                    set__sma_asal.setText(sma_asal);
                    set__hobby.setText(hobby);
                    set__ekskul.setText(ekskul);
                    set__nama_ortu.setText(nama_ortu);
                    set__alamat_ortu.setText(alamat_ortu);
                    set__email_ortu.setText(email_ortu);
                    set__mobilephone_ortu.setText(mobilephone_ortu);
                    set__id_fb.setText(id_fb);
                    set__id_ig.setText(id_ig);
                    set__id_line.setText(id_line);
                    set__numb_wa.setText(numb_wa);
                }}

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t){
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void F0_updateBiodata(){
        Form_Mhs_00.Form_Mhs_00.finish();
        Call<UserDataResponse> userbiodataupdate = mApiService.userbiodataupdate(
                String.valueOf(mPrefs.getUserID()),
                set__email.getText().toString(),
                set__email2.getText().toString(),
                set__mobile_phone.getText().toString(),
                set__mobile_phone2.getText().toString(),
                set__alamat_mlg.getText().toString(),
                set__alamat_asal.getText().toString(),
                set__sma_asal.getText().toString(),
                set__hobby.getText().toString(),
                set__ekskul.getText().toString(),
                set__nama_ortu.getText().toString(),
                set__alamat_ortu.getText().toString(),
                set__email_ortu.getText().toString(),
                set__mobilephone_ortu.getText().toString(),
                set__id_fb.getText().toString(),
                set__id_ig.getText().toString(),
                set__id_line.getText().toString(),
                set__numb_wa.getText().toString()
        );
        userbiodataupdate.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                String iserror = response.body().getIserror();
                if (iserror.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    Toast.makeText(mContext, "UPDATE BIODATA BERHASIL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Form_Mhs_00.class);
                    startActivity(intent);
                    finish();
                } else {

                Toast.makeText(mContext, "UPLOAD GAGAL.., Periksa jaringan anda!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Form_Mhs_00.class);
                startActivity(intent);
                finish();
            }}

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t){
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Toast.makeText(mContext, "UPLOAD GAGAL.., Harap isi semua data!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Form_Mhs_00.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void SelectImage(){

        final CharSequence[] items={"Camera","Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Form_Mhs_00_edit.this);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {

                    takeImageFromCamera();

                } else if (items[i].equals("Gallery")) {

                    getImageFromGallery();

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if(resultCode== Activity.RESULT_OK){

            if(requestCode==REQUEST_CAMERA){

                Bitmap mphoto = (Bitmap) data.getExtras().get("data");
                //panggil method uploadImage
                uploadImage(mphoto);

            }else if(requestCode==SELECT_FILE){

                Uri imageUri = data.getData();
                try {
                    Bitmap mphoto= MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    uploadImage(mphoto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void takeImageFromCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    public void getImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/png");
        startActivityForResult(intent, SELECT_FILE);

    }


    /*
    TODO mengconvert Bitmap menjadi file dikarenakan retrofit hanya mengenali tipe file untuk upload gambarnya sekaligus mengcompressnya menjadi WEBP dikarenakan size bisa sangat kecil dan kualitasnya pun setara dengan PNG.
    */
    private File createTempFile(Bitmap bitmap) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() +"_image.png");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,0, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void uploadImage(Bitmap gambarbitmap){
        Form_Mhs_00.Form_Mhs_00.finish();
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("no", createPartFromString(String.valueOf(mPrefs.getUserID())));
    //convert gambar jadi File terlebih dahulu dengan memanggil createTempFile yang di atas tadi.
        File file = createTempFile(gambarbitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
    // finally, kirim map dan body pada param interface retrofit
        Call<UserDataResponse> uploadImage = mApiService.uploadImage(
                body,
                map
                );
        uploadImage.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                String iserror = response.body().getIserror();
                if (response.isSuccessful()) {
                    Log.i("debug", "onResponse: BERHASIL");
                    if (iserror.equals("false")) {
                        // Jika login berhasil maka data nama yang ada di response API
                        // akan diparsing ke activity selanjutnya.
                        Toast.makeText(mContext, "UPLOAD BERHASIL", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, Form_Mhs_00.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(mContext, "UPLOAD GAGAL", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, Form_Mhs_00.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(mContext, "UPLOAD GAGAL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Form_Mhs_00.class);
                    startActivity(intent);
                    finish();
                }
        }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t){

                Toast.makeText(mContext, "UPLOAD GAGAL", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Intent intent = new Intent(mContext, Form_Mhs_00.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    public void F0_getPhoto(){
        Call<UserDataResponse> getPhoto = mApiService.getImage(
                String.valueOf(mPrefs.getUserID()));
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
}
