package com.example.bimbinganpasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bimbinganpasi.Data.ListForm4Response;
import com.example.bimbinganpasi.Data.List_Form4;
import com.example.bimbinganpasi.Data.Porto_Mhs;
import com.example.bimbinganpasi.Data.PortofolioMhsResponse;
import com.example.bimbinganpasi.Form_03.DataNote;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_03 extends AppCompatActivity {

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;
    Context mContext;

    RecyclerView listview;
    ListAdapter mListadapter;

    int [] no_list;
    String [] kegiatan_list,keterangan_list,kategori_list,semester_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_03);

        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;

        listview = (RecyclerView)findViewById(R.id.form_mhs_03_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(layoutManager);

        initListView();
    }

    public void initListView(){
        Call<PortofolioMhsResponse> getPortoMhs = mApiService.getPortoMhs(
                mPrefs.getUserID()
        );
        getPortoMhs.enqueue(new Callback<PortofolioMhsResponse>() {
            @Override
            public void onResponse(Call<PortofolioMhsResponse> call, Response<PortofolioMhsResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<Porto_Mhs> list = new ArrayList<>();
                    list = response.body().getPortoMhs();
                    no_list = new int[list.size()];
                    kegiatan_list = new String[list.size()];
                    keterangan_list = new String[list.size()];
                    kategori_list = new String[list.size()];
                    semester_list = new String[list.size()];
                    for (int i =0;i<list.size();i++) {
                        no_list[i] = list.get(i).getNo();
                        kegiatan_list[i] = list.get(i).getKegiatan();
                        keterangan_list[i] = list.get(i).getKeterangan();
                        kategori_list[i] = list.get(i).getKategori();
                        semester_list[i]  = list.get(i).getSemester();
                    }

                    ArrayList data = new ArrayList<DataNote>();
                    for (int i = 0; i < list.size(); i++)
                    {
                        data.add(
                                new DataNote
                                        (
                                                no_list[i],
                                                kegiatan_list[i],
                                                keterangan_list[i],
                                                kategori_list[i],
                                                semester_list[i]
                                        ));
                    }

                    mListadapter = new ListAdapter(data);
                    listview.setAdapter(mListadapter);

                }
            }
            @Override
            public void onFailure(Call<PortofolioMhsResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Jaringan Bermasalah", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
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
            TextView textViewKegiatan;
            TextView textViewKeterangan;
            TextView textViewKategori;
            TextView textViewSemester;
            ImageView imageViewDelete;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewSemester = (TextView) itemView.findViewById(R.id.TV03_cardview_semester);
                this.textViewKegiatan = (TextView) itemView.findViewById(R.id.TV03_cardview_kegiatan);
                this.textViewKeterangan = (TextView) itemView.findViewById(R.id.TV03_cardview_keterangan);
                this.textViewKategori = (TextView) itemView.findViewById(R.id.TV03_cardview_kategori);
                this.imageViewDelete = (ImageView) itemView.findViewById(R.id.IV03_cardview_delete);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_mhs_03_cardview, parent, false);

            ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            if(mPrefs.getUserType().equalsIgnoreCase("dosen")){
                holder.imageViewDelete.setVisibility(View.GONE);
            }
            holder.textViewKegiatan.setText(dataList.get(position).getKegiatan());
            holder.textViewKeterangan.setText(dataList.get(position).getKeterangan());
            holder.textViewKategori.setText(dataList.get(position).getKategori());
            holder.textViewSemester.setText(dataList.get(position).getSemester());
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
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
