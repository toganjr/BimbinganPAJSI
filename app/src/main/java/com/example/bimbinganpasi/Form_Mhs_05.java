package com.example.bimbinganpasi;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bimbinganpasi.Data.Image;
import com.example.bimbinganpasi.Data.LogbookMhsResponse;
import com.example.bimbinganpasi.Data.Logbook_Mhs;
import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.Porto_Mhs;
import com.example.bimbinganpasi.Data.PortofolioMhsResponse;
import com.example.bimbinganpasi.Form_05.DataNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    FloatingActionButton fab_add;
    public static Activity Form_Mhs_05;

    int [] no_list,status_list;
    String [] materi_list,tanggal_list,semester_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_05);

        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;
        Form_Mhs_05 = this;

        fab_add = (FloatingActionButton) findViewById(R.id.fab5_add);
        listview = (RecyclerView)findViewById(R.id.form_mhs_05_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(layoutManager);

        checkUserType();

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),Form_Mhs_05_tambah.class);
                startActivity(i);
            }
        });

    }

    public void initListView(String UserId){
        Call<LogbookMhsResponse> getLogbookMhs = mApiService.getLogbookMhs(
                String.valueOf(UserId)
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
                    status_list = new int[list.size()];
                    for (int i =0;i<list.size();i++) {
                        no_list[i] = list.get(i).getNo();
                        materi_list[i] = list.get(i).getMateri();
                        tanggal_list[i] = list.get(i).getTanggal();
                        semester_list[i]  = String.valueOf(list.get(i).getSemester());
                        status_list[i] = list.get(i).getStatus();
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
                                                semester_list[i],
                                                status_list[i]
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

    public void checkUserType(){
        if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
            initListView(String.valueOf(mPrefs.getUserID()));
        } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            initListView(String.valueOf(mPrefs.getSelectedUserId()));
            fab_add.hide();
        }
    }

    public void deleteLogbook(int no_logbook){
        Call<MessageResponse> deleteLogbook = mApiService.deleteLogbook(
                no_logbook
        );
        deleteLogbook.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Logbook telah dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(getBaseContext(),Form_Mhs_05.class);
                    startActivity(i);
                } else {
                    Toast.makeText(mContext, "Gagal menghapus Portofolio", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void konfirmasiLogbook(int no_logbook){
        Call<MessageResponse> konfirmasiLogbook = mApiService.konfirmasiLogbook(
                no_logbook
        );
        konfirmasiLogbook.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Logbook telah diACC", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(getBaseContext(),Form_Mhs_05.class);
                    startActivity(i);
                } else {
                    Toast.makeText(mContext, "Logbook gagal diACC", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
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

            } else {
                holder.imageViewDelete.setVisibility(View.GONE);
                if (dataList.get(position).getStatus() == 0){
                    holder.itemView.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            new AlertDialog.Builder(mContext)
                                    .setTitle("ACC Logbook")
                                    .setMessage("Are you sure you want to ACC this Logbook?")

                                    // Specifying a listener allows you to take an action before dismissing the dialog.
                                    // The dialog is automatically dismissed when a dialog button is clicked.
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Continue with delete operation
                                            konfirmasiLogbook(dataList.get(position).getNo());
                                        }
                                    })

                                    // A null listener allows the button to dismiss the dialog and take no further action.
                                    .setNegativeButton(android.R.string.no, null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    });
                } else {
                    
                }
            }
            if (dataList.get(position).getStatus() == 0){
                holder.textViewMateri.setTextColor(Color.rgb(255,0,0));
                holder.textViewTanggal.setTextColor(Color.rgb(255,0,0));
                holder.textViewSemester.setTextColor(Color.rgb(255,0,0));
            } else {
                holder.textViewMateri.setTextColor(Color.rgb(0,0,0));
                holder.textViewTanggal.setTextColor(Color.rgb(0,87,75));
                holder.textViewSemester.setTextColor(Color.rgb(0,87,75));
            }
            holder.textViewMateri.setText(dataList.get(position).getMateri());
            holder.textViewTanggal.setText(dataList.get(position).getTanggal());
            holder.textViewSemester.setText(dataList.get(position).getSemester());
            holder.imageViewDelete.setOnClickListener(new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(mContext)
                        .setTitle("Delete Logbook")
                        .setMessage("Are you sure you want to delete this Logbook?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                deleteLogbook(dataList.get(position).getNo());
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }});
        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }
    }

}
