package com.example.bimbinganpasi.Form_04.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.bimbinganpasi.Data.ListForm4Response;
import com.example.bimbinganpasi.Data.List_Form4;
import com.example.bimbinganpasi.Form_04.adapter.DataNote;
import com.example.bimbinganpasi.Form_Mhs_04_tambahcatatan;
import com.example.bimbinganpasi.Form_Mhs_Menu;
import com.example.bimbinganpasi.PreferencesHelper;
import com.example.bimbinganpasi.R;
import com.example.bimbinganpasi.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class form_mhs_04_fragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;

    RecyclerView listview;
    ListAdapter mListadapter;

    String [] ipk_cap,ipk_kat,sks_cap,sks_kat,catatan,semester;

    public form_mhs_04_fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.form_mhs_04_fragment2, container, false);
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getActivity().getApplication()).getPrefs();

        listview = (RecyclerView) v.findViewById(R.id.form_mhs_04_fragment2list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(layoutManager);

        checkUserType();

        // Inflate the layout for this fragment
        return v;
    }

    public void initListView(String UserId){
        Call<ListForm4Response> getListForm4 = mApiService.getListForm4(
                UserId
        );
        getListForm4.enqueue(new Callback<ListForm4Response>() {
            @Override
            public void onResponse(Call<ListForm4Response> call, Response<ListForm4Response> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<List_Form4> list = new ArrayList<>();
                    list = response.body().getEvalMhsf4();
                    ipk_cap = new String[list.size()];
                    ipk_kat = new String[list.size()];
                    sks_cap = new String[list.size()];
                    sks_kat = new String[list.size()];
                    catatan = new String[list.size()];
                    semester = new String[list.size()];
                    for (int i =0;i<list.size();i++) {
                        ipk_cap[i] = list.get(i).getIpkMhs();
                        ipk_kat[i] = list.get(i).getKategoriIpk();
                        sks_cap[i] = String.valueOf(list.get(i).getSksMhs());
                        sks_kat[i] = list.get(i).getKategoriSks();
                        catatan[i] = list.get(i).getCatatan();
                        semester[i]  = list.get(i).getSemester();
                    }

                    ArrayList data = new ArrayList<DataNote>();
                    for (int i = 0; i < list.size(); i++)
                    {
                        data.add(
                                new DataNote
                                        (
                                                ipk_cap[i],
                                                ipk_kat[i],
                                                sks_cap[i],
                                                sks_kat[i],
                                                catatan[i],
                                                semester[i]
                                        ));
                    }

                    mListadapter = new form_mhs_04_fragment2.ListAdapter(data);
                    listview.setAdapter(mListadapter);

                }
            }
            @Override
            public void onFailure(Call<ListForm4Response> call, Throwable t) {
                Toast.makeText(getActivity().getBaseContext(), "Koneksi Jaringan Bermasalah", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Intent intent = new Intent(getActivity().getBaseContext(), Form_Mhs_Menu.class);
                getActivity().startActivity(intent);
            }
        });
    }

    public void checkUserType(){
        if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
            initListView(String.valueOf(mPrefs.getUserID()));

        } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            initListView(String.valueOf(mPrefs.getSelectedUserId()));
        }
    }

    public class ListAdapter extends RecyclerView.Adapter<form_mhs_04_fragment2.ListAdapter.ViewHolder>
    {
        private ArrayList<DataNote> dataList;

        public ListAdapter(ArrayList<DataNote> data)
        {
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textViewIPKcap;
            TextView textViewIPKkat;
            TextView textViewSKScap;
            TextView textViewSKSkat;
            TextView textViewCatatan;
            TextView textViewSemester;
            Button btnCatatan;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewSemester = (TextView) itemView.findViewById(R.id.TV04_cardview_semester);
                this.textViewIPKcap = (TextView) itemView.findViewById(R.id.TV04_cardview_ipkcap);
                this.textViewIPKkat = (TextView) itemView.findViewById(R.id.TV04_cardview_ipkkat);
                this.textViewSKScap = (TextView) itemView.findViewById(R.id.TV04_cardview_skscap);
                this.textViewSKSkat = (TextView) itemView.findViewById(R.id.TV04_cardview_skskat);
                this.textViewCatatan = (TextView) itemView.findViewById(R.id.TV04_cardview_catatan);
                this.btnCatatan = (Button) itemView.findViewById(R.id.BTN04_cardview);
            }
        }

        @Override
        public form_mhs_04_fragment2.ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_mhs_04_cardview, parent, false);

            form_mhs_04_fragment2.ListAdapter.ViewHolder viewHolder = new form_mhs_04_fragment2.ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(form_mhs_04_fragment2.ListAdapter.ViewHolder holder, final int position)
        {

            holder.textViewIPKkat.setText(dataList.get(position).getIpkkat());
            holder.textViewIPKcap.setText("IPK : "+dataList.get(position).getIpkcap());
            holder.textViewSKSkat.setText(dataList.get(position).getSkskat());
            holder.textViewSKScap.setText("SKS : "+dataList.get(position).getSkscap()+" SKS");
            holder.textViewCatatan.setText(dataList.get(position).getCatatan());
            holder.textViewSemester.setText(dataList.get(position).getSemester());
            if(holder.textViewSKSkat.getText().equals("kritis") || holder.textViewSKSkat.getText().equals("kurang")){
                holder.textViewSKSkat.setTextColor(Color.rgb(255,0,0));
            } else {
                holder.textViewSKSkat.setTextColor(Color.rgb(0,0,0));
            }
            if(holder.textViewIPKkat.getText().equals("kritis") || holder.textViewIPKkat.getText().equals("kurang")){
                holder.textViewIPKkat.setTextColor(Color.rgb(255,0,0));
            } else {
                holder.textViewIPKkat.setTextColor(Color.rgb(0,0,0));
            }
            if (mPrefs.getUserType().equalsIgnoreCase("dosen")) {
                if (position+1  != (Integer.parseInt(mPrefs.getUserSmt())-1)) {
                    holder.btnCatatan.setVisibility(View.GONE);
                }
            } else {
                holder.btnCatatan.setVisibility(View.GONE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Catatan "+dataList.get(position).getSemester())
                            .setMessage(dataList.get(position).getCatatan())

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton("Close", null)
                            .setIcon(R.drawable.ic_speaker_notes_black_24dp)
                            .show();
                }
            });
            holder.btnCatatan.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(getContext(), Form_Mhs_04_tambahcatatan.class);
                    startActivity(intent);
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