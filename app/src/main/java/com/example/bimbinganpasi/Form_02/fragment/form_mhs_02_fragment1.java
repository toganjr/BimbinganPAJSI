package com.example.bimbinganpasi.Form_02.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bimbinganpasi.BaseAPIService;
import com.example.bimbinganpasi.BimbPA;
import com.example.bimbinganpasi.Data.CatatanResponse;
import com.example.bimbinganpasi.Data.EvalMhsResponse;
import com.example.bimbinganpasi.Data.EvalParaResponse;
import com.example.bimbinganpasi.Data.Eval_Mhs;
import com.example.bimbinganpasi.Data.IPKMhsResponse;
import com.example.bimbinganpasi.Data.IPKResponse;
import com.example.bimbinganpasi.Data.MatkulMhsResponse;
import com.example.bimbinganpasi.Data.Matkul_Mhs;
import com.example.bimbinganpasi.PreferencesHelper;
//import com.example.bimbinganpasi.Form_02.adapter.CustomAdapter;
import com.example.bimbinganpasi.Form_02.adapter.DataNote;
import com.example.bimbinganpasi.Form_Mhs_02_Detail;
import com.example.bimbinganpasi.Form_Mhs_02_tambah;
import com.example.bimbinganpasi.Form_Mhs_Menu;
import com.example.bimbinganpasi.R;
import com.example.bimbinganpasi.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class form_mhs_02_fragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;

    String [] no_list,matkul_list,sks_list,prasyarat_list,target_list,realisasi_list,nxk_list;
    String [] no_list2,matkul_list2,sks_list2,prasyarat_list2,target_list2,realisasi_list2,nxk_list2;

    TextView tv_ip1,tv_ipk1,tv_ip2,tv_ipk2,tv_realsks,tv_evalsks,tv_realipk,tv_evalipk,tv_parasks,tv_paraipk,tv_realsks2,tv_evalsks2,tv_realipk2,tv_evalipk2,tv_parasks2,tv_paraipk2
            ,tv_catatanodd,tv_catatanodd2,tv_catataneven,tv_catataneven2;
    Button btnF2_semester1,btnF2_semester2;
    RecyclerView listview1,listview2;
    ListAdapter mListadapter;
    ListAdapter2 mListadapter2;
    ViewGroup btn_smt1,btn_smt2;

    public form_mhs_02_fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.form_mhs_02_fragment1, container, false);
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getActivity().getApplication()).getPrefs();

        tv_ip1 = (TextView) v.findViewById(R.id.TVF2_fragment1_ip1);
        tv_ipk1 = (TextView) v.findViewById(R.id.TVF2_fragment1_ipk1);
        tv_ip2 = (TextView) v.findViewById(R.id.TVF2_fragment1_ip2);
        tv_ipk2 = (TextView) v.findViewById(R.id.TVF2_fragment1_ipk2);
        tv_realsks = (TextView) v.findViewById(R.id.TVF2_fragment1_realisasisks);
        tv_evalsks = (TextView) v.findViewById(R.id.TVF2_fragment1_evaluasisks);
        tv_realipk = (TextView) v.findViewById(R.id.TVF2_fragment1_realisasiipk);
        tv_evalipk = (TextView) v.findViewById(R.id.TVF2_fragment1_evaluasiipk);
        tv_parasks = (TextView) v.findViewById(R.id.TVF2_fragment1_parametersks);
        tv_paraipk = (TextView) v.findViewById(R.id.TVF2_fragment1_parameteripk);
        tv_realsks2 = (TextView) v.findViewById(R.id.TVF2_fragment1_realisasisks2);
        tv_evalsks2 = (TextView) v.findViewById(R.id.TVF2_fragment1_evaluasisks2);
        tv_realipk2 = (TextView) v.findViewById(R.id.TVF2_fragment1_realisasiipk2);
        tv_evalipk2 = (TextView) v.findViewById(R.id.TVF2_fragment1_evaluasiipk2);
        tv_parasks2 = (TextView) v.findViewById(R.id.TVF2_fragment1_parametersks2);
        tv_paraipk2 = (TextView) v.findViewById(R.id.TVF2_fragment1_parameteripk2);
        tv_catatanodd = (TextView) v.findViewById(R.id.catatansmt1);
        tv_catatanodd2 = (TextView) v.findViewById(R.id.catatan2smt1);
        tv_catataneven = (TextView) v.findViewById(R.id.catatansmt2);
        tv_catataneven2 = (TextView) v.findViewById(R.id.catatan2smt2);
        btnF2_semester1 = (Button) v.findViewById(R.id.btnF2_tambahsmt1);
        btnF2_semester2 = (Button) v.findViewById(R.id.btnF2_tambahsmt2);
        listview1 =(RecyclerView) v.findViewById(R.id.form_mhs_02_fragment1_list1);
        listview2 =(RecyclerView) v.findViewById(R.id.form_mhs_02_fragment1_list2);

        btn_smt1 = (ViewGroup) btnF2_semester1.getParent();
        btn_smt2 = (ViewGroup) btnF2_semester2.getParent();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listview1.setLayoutManager(layoutManager);

        final LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        listview2.setLayoutManager(layoutManager2);

        initListView1();
        initListView2();
        getIPKSemester(1);
        getIPKSemester(2);
        getEvalMhs();
        getEvalMhs2();
        getParameter();
        getParameter2();
        getCatatanodd();
        getCatatanEven();
        checkUserType(mPrefs.getUserType());

        btnF2_semester1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSemesterOdd();
            }
        });

        btnF2_semester2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSemesterEven();
            }
        });

        return v;
    }

    private void getSemesterOdd()
    {
        Intent i = new Intent(getActivity().getBaseContext(),
                Form_Mhs_02_tambah.class);
        i.putExtra("semester_min", 1);
        getActivity().startActivity(i);
    }
    private void getSemesterEven()
    {
        Intent i = new Intent(getActivity().getBaseContext(),
                Form_Mhs_02_tambah.class);
        i.putExtra("semester_min", 2);
        getActivity().startActivity(i);
    }

        public void initListView1(){
        Call<MatkulMhsResponse> getMatkulMhs = mApiService.getMatkulMhs(
                mPrefs.getUserID(),
                1
        );
        getMatkulMhs.enqueue(new Callback<MatkulMhsResponse>() {
            @Override
            public void onResponse(Call<MatkulMhsResponse> call, Response<MatkulMhsResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<Matkul_Mhs> list = new ArrayList<>();
                    list = response.body().getMatkulMhs();
                    no_list = new String[list.size()];
                    matkul_list = new String[list.size()];
                    sks_list = new String[list.size()];
                    prasyarat_list = new String[list.size()];
                    target_list = new String[list.size()];
                    realisasi_list = new String[list.size()];
                    nxk_list = new String[list.size()];
                    for (int i =0;i<list.size();i++) {
                        no_list[i] = list.get(i).getNo();
                        matkul_list[i] = list.get(i).getNamaMatkul();
                        sks_list[i] = list.get(i).getSks();
                        prasyarat_list[i] = list.get(i).getMatkulPrasyarat();
                        target_list[i] = list.get(i).getTarget();
                        realisasi_list[i] = list.get(i).getRealisasi();
                        nxk_list[i]  = list.get(i).getNxk();
                    }

                    ArrayList data = new ArrayList<DataNote>();
                    for (int i = 0; i < list.size(); i++)
                    {
                        data.add(
                                new DataNote
                                        (
                                                matkul_list[i],
                                                sks_list[i]
                                        ));
                    }

                    mListadapter = new ListAdapter(data);
                    listview1.setAdapter(mListadapter);

                }
            }
                    @Override
                    public void onFailure(Call<MatkulMhsResponse> call, Throwable t) {
                        Toast.makeText(getActivity().getBaseContext(), "Koneksi Jaringan Bermasalah", Toast.LENGTH_SHORT).show();
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        Intent intent = new Intent(getActivity().getBaseContext(), Form_Mhs_Menu.class);
                        getActivity().startActivity(intent);
                    }
                });
    }

    public void initListView2(){
        Call<MatkulMhsResponse> getMatkulMhs = mApiService.getMatkulMhs(
                mPrefs.getUserID(),
                2
        );
        getMatkulMhs.enqueue(new Callback<MatkulMhsResponse>() {
            @Override
            public void onResponse(Call<MatkulMhsResponse> call, Response<MatkulMhsResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<Matkul_Mhs> list = new ArrayList<>();
                    list = response.body().getMatkulMhs();
                    no_list2 = new String[list.size()];
                    matkul_list2 = new String[list.size()];
                    sks_list2 = new String[list.size()];
                    prasyarat_list2 = new String[list.size()];
                    target_list2 = new String[list.size()];
                    realisasi_list2 = new String[list.size()];
                    nxk_list2 = new String[list.size()];
                    for (int i =0;i<list.size();i++) {
                        no_list2[i] = list.get(i).getNo();
                        matkul_list2[i] = list.get(i).getNamaMatkul();
                        sks_list2[i] = list.get(i).getSks();
                        prasyarat_list2[i] = list.get(i).getMatkulPrasyarat();
                        target_list2[i] = list.get(i).getTarget();
                        realisasi_list2[i] = list.get(i).getRealisasi();
                        nxk_list2[i]  = list.get(i).getNxk();
                    }

                    ArrayList data = new ArrayList<DataNote>();
                    for (int i = 0; i < list.size(); i++)
                    {
                        data.add(
                                new DataNote
                                        (
                                                matkul_list2[i],
                                                sks_list2[i]
                                        ));
                    }

                    mListadapter2 = new ListAdapter2(data);
                    listview2.setAdapter(mListadapter2);

                }
            }
            @Override
            public void onFailure(Call<MatkulMhsResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void getIPKSemester(int semester){
        Call<IPKResponse> getIPKSemester = mApiService.getIPKMhs(
                mPrefs.getUserID(),
                semester
        );
        getIPKSemester.enqueue(new Callback<IPKResponse>() {
            @Override
            public void onResponse(Call<IPKResponse> call, Response<IPKResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<IPKMhsResponse> list = new ArrayList<>();
                    list = response.body().getIpk_mhs();

                    for (int i =0;i<list.size();i++) {
                        String ip = list.get(i).getIp();
                        String ipk = list.get(i).getIpk();
                        if (semester == 1) {
                            tv_ip1.setText(ip);
                            tv_ipk1.setText(ipk);
                        } else {
                            tv_ip2.setText(ip);
                            tv_ipk2.setText(ipk);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<IPKResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void getEvalMhs(){
        Call<EvalMhsResponse> getEvalMhs = mApiService.getEvalMhs(
                mPrefs.getUserID(),
                1
        );
        getEvalMhs.enqueue(new Callback<EvalMhsResponse>() {
            @Override
            public void onResponse(Call<EvalMhsResponse> call, Response<EvalMhsResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<Eval_Mhs> list = new ArrayList<>();
                    list = response.body().getEvalMhs();

                    for (int i =0;i<list.size();i++) {
                        String kategori_ipk = list.get(i).getKategoriIpk();
                        String ipk = list.get(i).getIpkMhs();
                        String kategori_sks = list.get(i).getKategoriSks();
                        String sks =  list.get(i).getSksMhs();
                        tv_evalipk.setText(kategori_ipk);
                        tv_evalsks.setText(kategori_sks);
                        tv_realipk.setText(ipk);
                        tv_realsks.setText(sks+" SKS");

                        if(kategori_ipk.equals("kurang") || kategori_ipk.equals("kritis")){
                            tv_evalipk.setTextColor(Color.rgb(255,0,0));
                        }

                        if (kategori_sks.equals("kurang") || kategori_sks.equals("kritis")){
                            tv_evalsks.setTextColor(Color.rgb(255,0,0));
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<EvalMhsResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void getEvalMhs2(){
        Call<EvalMhsResponse> getEvalMhs = mApiService.getEvalMhs(
                mPrefs.getUserID(),
                2
        );
        getEvalMhs.enqueue(new Callback<EvalMhsResponse>() {
            @Override
            public void onResponse(Call<EvalMhsResponse> call, Response<EvalMhsResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<Eval_Mhs> list = new ArrayList<>();
                    list = response.body().getEvalMhs();

                    for (int i =0;i<list.size();i++) {
                        String kategori_ipk = list.get(i).getKategoriIpk();
                        String ipk = list.get(i).getIpkMhs();
                        String kategori_sks = list.get(i).getKategoriSks();
                        String sks =  list.get(i).getSksMhs();
                        tv_evalipk2.setText(kategori_ipk);
                        tv_evalsks2.setText(kategori_sks);
                        tv_realipk2.setText(ipk);
                        tv_realsks2.setText(sks+" SKS");

                        if(kategori_ipk.equals("kurang") || kategori_ipk.equals("kritis")){
                            tv_evalipk2.setTextColor(Color.rgb(255,0,0));
                        }

                        if (kategori_sks.equals("kurang") || kategori_sks.equals("kritis")){
                            tv_evalsks2.setTextColor(Color.rgb(255,0,0));
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<EvalMhsResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void getParameter(){
        Call<EvalParaResponse> getParameter = mApiService.getEvalPara(
                1
        );
        getParameter.enqueue(new Callback<EvalParaResponse>() {
            @Override
            public void onResponse(Call<EvalParaResponse> call, Response<EvalParaResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    String parasks = String.valueOf(response.body().getSks());
                    String paraipk = String.valueOf(response.body().getIpk());
                    tv_parasks.setText(parasks+" SKS");
                    tv_paraipk.setText(paraipk);
                }
            }
            @Override
            public void onFailure(Call<EvalParaResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void getParameter2(){
        Call<EvalParaResponse> getParameter = mApiService.getEvalPara(
                2
        );
        getParameter.enqueue(new Callback<EvalParaResponse>() {
            @Override
            public void onResponse(Call<EvalParaResponse> call, Response<EvalParaResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    String parasks2 = String.valueOf(response.body().getSks());
                    String paraipk2 = String.valueOf(response.body().getIpk());
                    tv_parasks2.setText(parasks2+" SKS");
                    tv_paraipk2.setText(paraipk2);
                }
            }
            @Override
            public void onFailure(Call<EvalParaResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void getCatatanodd(){
        Call<CatatanResponse> getParameter = mApiService.getCatatanForm2(
                mPrefs.getUserID(),
                1
        );
        getParameter.enqueue(new Callback<CatatanResponse>() {
            @Override
            public void onResponse(Call<CatatanResponse> call, Response<CatatanResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    String metpen = String.valueOf(response.body().getMetpen());
                    String pkl1 = String.valueOf(response.body().getPkl1());
                    String pkl2 = String.valueOf(response.body().getPkl2());
                    String skripsi = String.valueOf(response.body().getSkripsi());
                    String lulus = String.valueOf(response.body().getLulus());
                    String catatan = "";
                    if (metpen != "null"){
                        catatan += metpen;}
                    if (pkl1 != "null"){
                        catatan += pkl1;}
                    if (pkl2 != "null"){
                        catatan += pkl2;}
                    if (skripsi != "null"){
                        catatan += skripsi;}
                    if (lulus != "null"){
                        catatan += lulus;}
                    tv_catatanodd.setText(catatan);
                }
            }
            @Override
            public void onFailure(Call<CatatanResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void getCatatanEven(){
        Call<CatatanResponse> getParameter = mApiService.getCatatanForm2(
                mPrefs.getUserID(),
                2
        );
        getParameter.enqueue(new Callback<CatatanResponse>() {
            @Override
            public void onResponse(Call<CatatanResponse> call, Response<CatatanResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    String metpen = String.valueOf(response.body().getMetpen());
                    String pkl1 = String.valueOf(response.body().getPkl1());
                    String pkl2 = String.valueOf(response.body().getPkl2());
                    String skripsi = String.valueOf(response.body().getSkripsi());
                    String lulus = String.valueOf(response.body().getLulus());
                    String catatan = "";
                    if (metpen != "null"){
                        catatan += metpen;}
                    if (pkl1 != "null"){
                        catatan += pkl1;}
                    if (pkl2 != "null"){
                        catatan += pkl2;}
                    if (skripsi != "null"){
                        catatan += skripsi;}
                    if (lulus != "null"){
                        catatan += lulus;}
                    tv_catataneven.setText(catatan);
                }
            }
            @Override
            public void onFailure(Call<CatatanResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }


    public void checkUserType(String typeUser){

        if(typeUser.equals("dosen")){
            btn_smt1.removeView(btnF2_semester1);
            btn_smt2.removeView(btnF2_semester2);
        } else if (typeUser.equals("mahasiswa")){
            if(Integer.valueOf(mPrefs.getUserSmt()) == 1) {
                btn_smt2.removeView(btnF2_semester2);
            } else if (Integer.valueOf(mPrefs.getUserSmt()) == 2){
                btn_smt1.removeView(btnF2_semester1);
            } else {
                btn_smt1.removeView(btnF2_semester1);
                btn_smt2.removeView(btnF2_semester2);
            }
        }
    }
    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<DataNote> dataList;

        public ListAdapter(ArrayList<DataNote> data)
        {
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textViewMatkul;
            TextView textViewSks;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewMatkul = (TextView) itemView.findViewById(R.id.tv_matkul);
                this.textViewSks = (TextView) itemView.findViewById(R.id.tv_sks);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_mhs_02_cardview, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.textViewMatkul.setText(dataList.get(position).getMatkul_list());
            holder.textViewSks.setText(dataList.get(position).getSks_list()+" SKS");
            if (realisasi_list[position].equals("")){
                holder.textViewMatkul.setTextColor(Color.rgb(255,0,0));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                       // Toast.makeText(getActivity(), "Item " + prasyarat_list[position] + " is clicked.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity().getBaseContext(),
                            Form_Mhs_02_Detail.class);
                    i.putExtra("no", no_list[position]);
                    i.putExtra("nama_matkul", matkul_list[position]);
                    i.putExtra("sks_matkul", sks_list[position]);
                    i.putExtra("prasyarat_matkul", prasyarat_list[position]);
                    i.putExtra("target_matkul", target_list[position]);
                    i.putExtra("realisasi_matkul", realisasi_list[position]);
                    i.putExtra("nxk_matkul", nxk_list[position]);
                    getActivity().startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }
    }

    public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.ViewHolder>
    {
        private ArrayList<DataNote> dataList;

        public ListAdapter2(ArrayList<DataNote> data)
        {
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textViewMatkul;
            TextView textViewSks;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewMatkul = (TextView) itemView.findViewById(R.id.tv_matkul);
                this.textViewSks = (TextView) itemView.findViewById(R.id.tv_sks);
            }
        }

        @Override
        public ListAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_mhs_02_cardview, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter2.ViewHolder holder, final int position)
        {
            holder.textViewMatkul.setText(dataList.get(position).getMatkul_list());
            holder.textViewSks.setText(dataList.get(position).getSks_list()+" SKS");
            if (realisasi_list2[position].equals("")){
                holder.textViewMatkul.setTextColor(Color.rgb(255,0,0));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent i = new Intent(getActivity().getBaseContext(),
                            Form_Mhs_02_Detail.class);
                    i.putExtra("no", no_list2[position]);
                    i.putExtra("nama_matkul", matkul_list2[position]);
                    i.putExtra("sks_matkul", sks_list2[position]);
                    i.putExtra("prasyarat_matkul", prasyarat_list2[position]);
                    i.putExtra("target_matkul", target_list2[position]);
                    i.putExtra("realisasi_matkul", realisasi_list2[position]);
                    i.putExtra("nxk_matkul", nxk_list2[position]);
                    getActivity().startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }
    }

}
