package com.example.bimbinganpasi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bimbinganpasi.Data.MilestoneDataResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_01_edit3 extends AppCompatActivity {

    EditText editText1;
    Button btnImg,btnSave,btnDelete;

    Integer REQUEST_CAMERA=1, SELECT_FILE=0;
    PreferencesHelper mPrefs;
    BaseAPIService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_01_edit3);

        mContext = this;
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();

        editText1 = (EditText) findViewById(R.id.et01_edit3wisuda);
        btnSave = (Button) findViewById(R.id.btn01_edit3save);
        btnImg = (Button) findViewById(R.id.btn01_edit3gambar);
        btnDelete = (Button) findViewById(R.id.btn01_delete3gambar);

        requestMilestoneData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editMilestoneData();
            }
        });

        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelectImage();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DeleteImage();
            }
        });
    }

    public void requestMilestoneData(){
        Call<MilestoneDataResponse> getMilestoneData = mApiService.MilestoneData(
                String.valueOf(mPrefs.getUserID()));
        getMilestoneData.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse> response) {
                String iserror_ = response.body().getError();
                if (iserror_.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    String wisuda = response.body().getWisuda();
                    editText1.setText(wisuda);
                }
            }
            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void editMilestoneData(){
        Form_Mhs_01.Form_Mhs_01.finish();
        Call<MilestoneDataResponse> milestoneedit03 = mApiService.edit03Milestone(
                String.valueOf(mPrefs.getUserID()),
                editText1.getText().toString()
        );
        milestoneedit03.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse> response) {
                String iserror = response.body().getError();
                if (iserror.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    Toast.makeText(mContext, "EDIT BERHASIL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Form_Mhs_01.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(mContext, "EDIT GAGAL., Periksa jaringan anda!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Form_Mhs_01.class);
                    startActivity(intent);
                    finish();
                }}

            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t){
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Toast.makeText(mContext, "UPLOAD GAGAL.., Harap isi semua data!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Form_Mhs_Menu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void SelectImage(){

        final CharSequence[] items={"Camera","Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Form_Mhs_01_edit3.this);
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
        Form_Mhs_01.Form_Mhs_01.finish();
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("no", createPartFromString(String.valueOf(mPrefs.getUserID())));
        //convert gambar jadi File terlebih dahulu dengan memanggil createTempFile yang di atas tadi.
        File file = createTempFile(gambarbitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        // finally, kirim map dan body pada param interface retrofit
        Call<MilestoneDataResponse> uploadImageWisuda = mApiService.uploadImageWisuda(
                body,
                map
        );
        uploadImageWisuda.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse> response) {
                String iserror = response.body().getError();
                if (response.isSuccessful()) {
                    Log.i("debug", "onResponse: BERHASIL");
                    if (iserror.equals("false")) {
                        // Jika login berhasil maka data nama yang ada di response API
                        // akan diparsing ke activity selanjutnya.
                        Toast.makeText(mContext, "UPLOAD BERHASIL", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, Form_Mhs_01.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(mContext, "UPLOAD GAGAL", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, Form_Mhs_01.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(mContext, "UPLOAD GAGAL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Form_Mhs_01.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t){

                Toast.makeText(mContext, "UPLOAD GAGAL", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Intent intent = new Intent(mContext, Form_Mhs_01.class);
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

    public void DeleteImage(){
        Form_Mhs_01.Form_Mhs_01.finish();
        Call<MilestoneDataResponse> deleteImageWisuda = mApiService.deleteImageWisuda(
                String.valueOf(mPrefs.getUserID()));
        deleteImageWisuda.enqueue(new Callback<MilestoneDataResponse>() {
            @Override
            public void onResponse(Call<MilestoneDataResponse> call, Response<MilestoneDataResponse> response) {
                String iserror_ = response.body().getError();
                if (iserror_.equals("false")) {
                    Toast.makeText(mContext, "DELETE BERHASIL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Form_Mhs_01.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onFailure(Call<MilestoneDataResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Toast.makeText(mContext, "DELETE GAGAL", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Form_Mhs_01.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
