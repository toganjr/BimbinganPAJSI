package com.example.bimbinganpasi.Form_04.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.bimbinganpasi.BaseAPIService;
import com.example.bimbinganpasi.BimbPA;
import com.example.bimbinganpasi.Data.IpList;
import com.example.bimbinganpasi.Data.IpListResponse;
import com.example.bimbinganpasi.PreferencesHelper;
import com.example.bimbinganpasi.R;
import com.example.bimbinganpasi.UtilsApi;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class form_mhs_04_fragment3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;
    IpListResponse formData;

    ArrayList<String> x;
    ArrayList<Entry> y;
    private LineChart mChart;

    public form_mhs_04_fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_mhs_04_fragment3, container, false);

        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getActivity().getApplication()).getPrefs();

        mChart = view.findViewById(R.id.chart);

        x = new ArrayList<String>();
        y = new ArrayList<Entry>();
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        XAxis xl = mChart.getXAxis();
        xl.setAvoidFirstLastClipping(true);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setInverted(false);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

        checkUserType();

        // Inflate the layout for this fragment
        return view;
    }

    public void getIPKList(String UserId){
        Call<IpListResponse> getIPKList = mApiService.getIPKList(
                UserId
        );
        getIPKList.enqueue(new Callback<IpListResponse>() {
            @Override
            public void onResponse(Call<IpListResponse> call, Response<IpListResponse> response) {
                if (response.isSuccessful()) {
                    List<IpList> list = new ArrayList<>();
                    list = response.body().getIpList();
                    x.add("");
                    y.add(new Entry(0,0));
                    for (int i = 0; i < list.size(); i++) {
                        String value = String.valueOf(list.get(i).getSemester());
                        double date = list.get(i).getIp();
                        x.add(value);
                        y.add(new Entry((float)date, i+1));
                    }
                    LineDataSet set2 = new LineDataSet(y,"Semester");
                    set2.setLineWidth(2f);
                    set2.setCircleRadius(2.5f);
                    set2.setColor(Color.rgb(0,128,0));
                    LineData data = new LineData(x, set2);
                    mChart.setData(data);
                    mChart.invalidate();

                }
            }
            @Override
            public void onFailure(Call<IpListResponse> call, Throwable t) {
            }
        });
    }

    public void checkUserType(){

        if(mPrefs.getUserType().equals("dosen")){
            getIPKList(String.valueOf(mPrefs.getSelectedUserId()));
        } else if (mPrefs.getUserType().equals("mahasiswa")){
            getIPKList(String.valueOf(mPrefs.getUserID()));
        }
    }

}