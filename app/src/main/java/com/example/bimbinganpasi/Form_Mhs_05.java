package com.example.bimbinganpasi;

import android.content.Context;
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

import com.example.bimbinganpasi.Data.Image;
import com.example.bimbinganpasi.Data.LogbookMhsResponse;
import com.example.bimbinganpasi.Data.Logbook_Mhs;
import com.example.bimbinganpasi.Data.Porto_Mhs;
import com.example.bimbinganpasi.Data.PortofolioMhsResponse;
import com.example.bimbinganpasi.Form_05.DataNote;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_05 extends AppCompatActivity {

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;
    Context mContext;

    RecyclerView listview;
    ListAdapter mListadapter;

    int [] no_list;
    String [] materi_list,tanggal_list,semester_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_05);

        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;

        listview = (RecyclerView)findViewById(R.id.form_mhs_05_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(layoutManager);

        initListView();

    }

    public void initListView(){
        Call<LogbookMhsResponse> getLogbookMhs = mApiService.getLogbookMhs(
                mPrefs.getUserID()
        );
        getLogbookMhs.enqueue(new Callback<LogbookMhsResponse>() {
            @Override
            public void onResponse(Call<LogbookMhsResponse> call, Response<LogbookMhsResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<Logbook_Mhs> list = new ArrayList<>();
                    list = response.body().getLogbookMhs();
                    no_list = new int[list.size()];
                    materi_list = new String[list.size()];
                    tanggal_list = new String[list.size()];
                    semester_list = new String[list.size()];
                    for (int i =0;i<list.size();i++) {
                        no_list[i] = list.get(i).getNo();
                        materi_list[i] = list.get(i).getMateri();
                        tanggal_list[i] = list.get(i).getTanggal();
                        semester_list[i]  = String.valueOf(list.get(i).getSemester());
                    }

                    ArrayList data = new ArrayList<DataNote>();
                    for (int i = 0; i < list.size(); i++)
                    {
                        data.add(
                                new DataNote
                                        (
                                                no_list[i],
                                                materi_list[i],
                                                tanggal_list[i],
                                                semester_list[i]
                                        ));
                    }

                    mListadapter = new ListAdapter(data);
                    listview.setAdapter(mListadapter);

                }
            }
            @Override
            public void onFailure(Call<LogbookMhsResponse> call, Throwable t) {
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
            TextView textViewMateri;
            TextView textViewTanggal;
            TextView textViewSemester;
            ImageView imageViewDelete;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewSemester = (TextView) itemView.findViewById(R.id.TV05_cardview_semester);
                this.textViewMateri = (TextView) itemView.findViewById(R.id.TV05_cardview_materi);
                this.textViewTanggal = (TextView) itemView.findViewById(R.id.TV05_cardview_tanggal);
                this.imageViewDelete = (ImageView) itemView.findViewById(R.id.IV05_cardview_delete);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_mhs_05_cardview, parent, false);

            ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
                holder.imageViewDelete.setVisibility(View.GONE);
            }
            holder.textViewMateri.setText(dataList.get(position).getMateri());
            holder.textViewTanggal.setText(dataList.get(position).getTanggal());
            holder.textViewSemester.setText(dataList.get(position).getSemester());
            holder.imageViewDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext, "Kamu memilih mata kuliah ", Toast.LENGTH_SHORT).show();
            }
        });
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
