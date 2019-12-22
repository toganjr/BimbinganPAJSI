package com.example.bimbinganpasi;

import com.example.bimbinganpasi.Data.CatatanEvalResponse;
import com.example.bimbinganpasi.Data.CatatanResponse;
import com.example.bimbinganpasi.Data.EvalMhsResponse;
import com.example.bimbinganpasi.Data.EvalParaResponse;
import com.example.bimbinganpasi.Data.IPKResponse;
import com.example.bimbinganpasi.Data.ListForm4Response;
import com.example.bimbinganpasi.Data.LogbookMhsResponse;
import com.example.bimbinganpasi.Data.MatkulList;
import com.example.bimbinganpasi.Data.MatkulMhsResponse;
import com.example.bimbinganpasi.Data.MhsBimbinganResponse;
import com.example.bimbinganpasi.Data.MilestoneDataResponse;
import com.example.bimbinganpasi.Data.PortofolioMhsResponse;
import com.example.bimbinganpasi.Data.UserDataResponse;
import com.example.bimbinganpasi.Data.UserIDResponse;
import com.example.bimbinganpasi.Data.tambahMatkulResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface BaseAPIService {

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
    @FormUrlEncoded
    @POST("include/login.php")
    Call<UserIDResponse> loginRequest(@Field("nomor_induk") String nomor_induk,
                                      @Field("password") String password);

    @FormUrlEncoded
    @POST("include/get_user.php")
    Call<UserDataResponse> loggedinuserrequest(@Field("no") String no);

    @FormUrlEncoded
    @POST("include/get_user_dosen.php")
    Call<UserDataResponse> loggedinuserrequestdosen(@Field("no") String no);

    @FormUrlEncoded
    @POST("include/getMhsBimbingan.php")
    Call<MhsBimbinganResponse> mhsBimbinganRequest(@Field("no") String no);

    @FormUrlEncoded
    @POST("include/get_userbiodata.php")
    Call<UserDataResponse> userbiodatarequest(@Field("no") String no);

    @FormUrlEncoded
    @POST("include/getMilestoneBio.php")
    Call<UserDataResponse> MilestoneBio(@Field("no") String no);

    @FormUrlEncoded
    @POST("include/getMilestoneData.php")
    Call<MilestoneDataResponse> MilestoneData(@Field("no") String no);

    @FormUrlEncoded
    @POST("include/editForm1_00.php")
    Call<MilestoneDataResponse> edit00Milestone(@Field("no") String no,
                                               @Field("ipk_target") String ipk_target,
                                               @Field("catatan_tambahan") String catatan_tambahan
                                               );

    @FormUrlEncoded
    @POST("include/editForm1_01.php")
    Call<MilestoneDataResponse> edit01Milestone(@Field("no") String no,
                                                @Field("pendaftaran") String pendaftaran,
                                                @Field("pelaksanaan") String pelaksanaan
    );

    @FormUrlEncoded
    @POST("include/editForm1_02.php")
    Call<MilestoneDataResponse> edit02Milestone(@Field("no") String no,
                                                @Field("pra") String pra,
                                                @Field("pengerjaan") String pengerjaan,
                                                @Field("semhas") String semhas
    );

    @FormUrlEncoded
    @POST("include/editForm1_03.php")
    Call<MilestoneDataResponse> edit03Milestone(@Field("no") String no,
                                                @Field("wisuda") String wisuda
    );

    @FormUrlEncoded
    @POST("include/edit_biodata.php")
    Call<UserDataResponse> userbiodataupdate(@Field("no") String no,
                                             @Field("email") String email,
                                             @Field("email2") String email2,
                                             @Field("mphone") String mphone,
                                             @Field("mphone2") String mphone2,
                                             @Field("alamatmlg") String alamatmlg,
                                             @Field("alamatasal") String alamatasal,
                                             @Field("asalsma") String asalsma,
                                             @Field("hobby") String hobby,
                                             @Field("ekskul") String ekskul,
                                             @Field("namaortu") String namaortu,
                                             @Field("alamatortu") String alamatortu,
                                             @Field("emailortu") String emailortu,
                                             @Field("mphoneortu") String mphoneortu,
                                             @Field("fb_id") String fb_id,
                                             @Field("ig_id") String ig_id,
                                             @Field("line_id") String line_id,
                                             @Field("wa_numb") String wa_numb);

    @FormUrlEncoded
    @POST("include/upload_img.php?apicall=getPhoto")
    Call<UserDataResponse> getImage(@Field("no") String no);

    @Multipart
    @POST("include/upload_img.php?apicall=upload")
    Call<UserDataResponse> uploadImage(@Part MultipartBody.Part photo,
                                       @PartMap Map<String,RequestBody> text);

    @FormUrlEncoded
    @POST("include/upload_imgpkl.php?apicall=getPhoto")
    Call<MilestoneDataResponse> getImagePkl(@Field("no") String no);

    @Multipart
    @POST("include/upload_imgpkl.php?apicall=upload")
    Call<MilestoneDataResponse> uploadImagePkl(@Part MultipartBody.Part photo,
                                       @PartMap Map<String,RequestBody> text);

    @FormUrlEncoded
    @POST("include/upload_imgpkl.php?apicall=deletePhoto")
    Call<MilestoneDataResponse> deleteImagePkl(@Field("no") String no);

    @FormUrlEncoded
    @POST("include/upload_imgskripsi.php?apicall=getPhoto")
    Call<MilestoneDataResponse> getImageSkripsi(@Field("no") String no);

    @Multipart
    @POST("include/upload_imgskripsi.php?apicall=upload")
    Call<MilestoneDataResponse> uploadImageSkripsi(@Part MultipartBody.Part photo,
                                               @PartMap Map<String,RequestBody> text);

    @FormUrlEncoded
    @POST("include/upload_imgskripsi.php?apicall=deletePhoto")
    Call<MilestoneDataResponse> deleteImageSkripsi(@Field("no") String no);

    @FormUrlEncoded
    @POST("include/upload_imgwisuda.php?apicall=getPhoto")
    Call<MilestoneDataResponse> getImageWisuda(@Field("no") String no);

    @Multipart
    @POST("include/upload_imgwisuda.php?apicall=upload")
    Call<MilestoneDataResponse> uploadImageWisuda(@Part MultipartBody.Part photo,
                                               @PartMap Map<String,RequestBody> text);

    @FormUrlEncoded
    @POST("include/upload_imgwisuda.php?apicall=deletePhoto")
    Call<MilestoneDataResponse> deleteImageWisuda(@Field("no") String no);

    @FormUrlEncoded
    @POST("include/getMatkulList.php")
    Call<MatkulList> getMatkulList(@Field("semester") int semester);

    @FormUrlEncoded
    @POST("include/tambahMatkul.php")
    Call<tambahMatkulResponse> tambahMatkulMhs(@Field("no") int no,
                                               @Field("no_matkul") int no_matkul,
                                               @Field("target") String target,
                                               @Field("semester") int semester);

    @FormUrlEncoded
    @POST("include/getMatkulMhs.php")
    Call<MatkulMhsResponse> getMatkulMhs(@Field("no") int no,
                                         @Field("semester") int semester);

    @FormUrlEncoded
    @POST("include/getIPKMhs.php")
    Call<IPKResponse> getIPKMhs(@Field("no") int no,
                                @Field("semester") int semester);

    @FormUrlEncoded
    @POST("include/getEvalMhs.php")
    Call<EvalMhsResponse> getEvalMhs(@Field("no") int no,
                                     @Field("semester") int semester);

    @FormUrlEncoded
    @POST("include/getEvalPara.php")
    Call<EvalParaResponse> getEvalPara(@Field("semester") int semester);

    @FormUrlEncoded
    @POST("include/getCatatanEval.php")
    Call<CatatanEvalResponse> getCatatan(@Field("semester") int semester);

    @FormUrlEncoded
    @POST("include/getCatatanForm2.php")
    Call<CatatanResponse> getCatatanForm2(@Field("no") int no,
                                            @Field("semester") int semester);

    @FormUrlEncoded
    @POST("include/getListform4.php")
    Call<ListForm4Response> getListForm4(@Field("no") int no);

    @FormUrlEncoded
    @POST("include/getPortoMhs.php")
    Call<PortofolioMhsResponse> getPortoMhs(@Field("no") int no);

    @FormUrlEncoded
    @POST("include/getLogbookMhs.php")
    Call<LogbookMhsResponse> getLogbookMhs(@Field("no") int no);

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
  // @FormUrlEncoded
   // @POST("register.php")
   // Call<ResponseBody> registerRequest(@Field("nama") String nama,
                  //                     @Field("email") String email,
                  //                     @Field("password") String password);
}
