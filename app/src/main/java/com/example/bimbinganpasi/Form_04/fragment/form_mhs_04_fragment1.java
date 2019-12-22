package com.example.bimbinganpasi.Form_04.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bimbinganpasi.BaseAPIService;
import com.example.bimbinganpasi.BimbPA;
import com.example.bimbinganpasi.Data.CatatanEvalResponse;
import com.example.bimbinganpasi.PreferencesHelper;
import com.example.bimbinganpasi.R;
import com.example.bimbinganpasi.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class form_mhs_04_fragment1 extends Fragment {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;

    TextView tv_min1,tv_normal1,tv_baik1
            ,tv_min2,tv_normal2,tv_baik2
            ,tv_min3,tv_normal3,tv_baik3
            ,tv_min4,tv_normal4,tv_baik4
            ,tv_min5,tv_normal5,tv_baik5
            ,tv_min6,tv_normal6,tv_baik6
            ,tv_min7,tv_normal7,tv_baik7
            ,tv_min8,tv_normal8,tv_baik8
            ,tv_min9,tv_normal9,tv_baik9
            ,tv_min10,tv_normal10,tv_baik10;


    public form_mhs_04_fragment1() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.form_mhs_04_fragment1, container, false);
            mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
            mPrefs = ((BimbPA) getActivity().getApplication()).getPrefs();

            tv_min1 = (TextView) v.findViewById(R.id.TV04_semester1_minsks);
            tv_normal1 = (TextView) v.findViewById(R.id.TV04_semester1_normalsks);
            tv_baik1 = (TextView) v.findViewById(R.id.TV04_semester1_baiksks);
            tv_min2 = (TextView) v.findViewById(R.id.TV04_semester2_minsks);
            tv_normal2 = (TextView) v.findViewById(R.id.TV04_semester2_normalsks);
            tv_baik2 = (TextView) v.findViewById(R.id.TV04_semester2_baiksks);
            tv_min3 = (TextView) v.findViewById(R.id.TV04_semester3_minsks);
            tv_normal3 = (TextView) v.findViewById(R.id.TV04_semester3_normalsks);
            tv_baik3 = (TextView) v.findViewById(R.id.TV04_semester3_baiksks);
            tv_min4 = (TextView) v.findViewById(R.id.TV04_semester4_minsks);
            tv_normal4 = (TextView) v.findViewById(R.id.TV04_semester4_normalsks);
            tv_baik4 = (TextView) v.findViewById(R.id.TV04_semester4_baiksks);
            tv_min5 = (TextView) v.findViewById(R.id.TV04_semester5_minsks);
            tv_normal5 = (TextView) v.findViewById(R.id.TV04_semester5_normalsks);
            tv_baik5 = (TextView) v.findViewById(R.id.TV04_semester5_baiksks);
            tv_min6 = (TextView) v.findViewById(R.id.TV04_semester6_minsks);
            tv_normal6 = (TextView) v.findViewById(R.id.TV04_semester6_normalsks);
            tv_baik6 = (TextView) v.findViewById(R.id.TV04_semester6_baiksks);
            tv_min7 = (TextView) v.findViewById(R.id.TV04_semester7_minsks);
            tv_normal7 = (TextView) v.findViewById(R.id.TV04_semester7_normalsks);
            tv_baik7 = (TextView) v.findViewById(R.id.TV04_semester7_baiksks);
            tv_min8 = (TextView) v.findViewById(R.id.TV04_semester8_minsks);
            tv_normal8 = (TextView) v.findViewById(R.id.TV04_semester8_normalsks);
            tv_baik8 = (TextView) v.findViewById(R.id.TV04_semester8_baiksks);
            tv_min9 = (TextView) v.findViewById(R.id.TV04_semester9_minsks);
            tv_normal9 = (TextView) v.findViewById(R.id.TV04_semester9_normalsks);
            tv_baik9 = (TextView) v.findViewById(R.id.TV04_semester9_baiksks);
            tv_min10 = (TextView) v.findViewById(R.id.TV04_semester10_minsks);
            tv_normal10 = (TextView) v.findViewById(R.id.TV04_semester10_normalsks);
            tv_baik10 = (TextView) v.findViewById(R.id.TV04_semester10_baiksks);

            for (int i = 1; i <= 10; i++){
                getCatatan(i);
            }

            // Inflate the layout for this fragment
            return v;
        }

    public void getCatatan(int semester){
        Call<CatatanEvalResponse> getCatatan = mApiService.getCatatan(
                semester
        );
        getCatatan.enqueue(new Callback<CatatanEvalResponse>() {
            @Override
            public void onResponse(Call<CatatanEvalResponse> call, Response<CatatanEvalResponse> response) {
                boolean iserror_ = response.body().getError();
                if (semester == 1) {
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min1.setText(sks_min+" SKS");
                    tv_normal1.setText(sks_normal+" SKS");
                    tv_baik1.setText(sks_baik+" <=");
                } else if (semester == 2) {
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min2.setText(sks_min+" SKS");
                    tv_normal2.setText(sks_normal+" SKS");
                    tv_baik2.setText(sks_baik+" <=");
                } else if (semester == 3) {
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min3.setText(sks_min+" SKS");
                    tv_normal3.setText(sks_normal+" SKS");
                    tv_baik3.setText(sks_baik+" <=");
                } else if (semester == 4){
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min4.setText(sks_min+" SKS");
                    tv_normal4.setText(sks_normal+" SKS");
                    tv_baik4.setText(sks_baik+" <=");
                } else if (semester == 5){
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min5.setText(sks_min+" SKS");
                    tv_normal5.setText(sks_normal+" SKS");
                    tv_baik5.setText(sks_baik+" <=");
                } else if (semester == 6){
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min6.setText(sks_min+" SKS");
                    tv_normal6.setText(sks_normal+" SKS");
                    tv_baik6.setText(sks_baik+" <=");
                } else if (semester == 7){
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min7.setText(sks_min+" SKS");
                    tv_normal7.setText(sks_normal+" SKS");
                    tv_baik7.setText(sks_baik+" <=");
                } else if (semester == 8){
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min8.setText(sks_min+" SKS");
                    tv_normal8.setText(sks_normal+" SKS");
                    tv_baik8.setText(sks_baik+" <=");
                } else if (semester == 9) {
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min9.setText(sks_min+" SKS");
                    tv_normal9.setText(sks_normal+" SKS");
                    tv_baik9.setText(sks_baik+" <=");
                } else {
                    String sks_min = String.valueOf(response.body().getMinSks());
                    String sks_normal = String.valueOf(response.body().getNormalSKS());
                    String sks_baik = String.valueOf(response.body().getBaikSks());
                    tv_min10.setText(sks_min+" SKS");
                    tv_normal10.setText(sks_normal+" SKS");
                    tv_baik10.setText(sks_baik+" <=");
                }
            }
            @Override
            public void onFailure(Call<CatatanEvalResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }


}
