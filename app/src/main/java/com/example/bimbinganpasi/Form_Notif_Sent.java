package com.example.bimbinganpasi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.example.bimbinganpasi.Data.Notif;
import com.example.bimbinganpasi.Data.NotifResponse;
import com.example.bimbinganpasi.Data.NotifSent;
import com.example.bimbinganpasi.Data.Porto_Mhs;
import com.example.bimbinganpasi.Data.PortofolioMhsResponse;
import com.example.bimbinganpasi.Data.SentNotifResponse;
import com.example.bimbinganpasi.Sent_Notif.DataNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Notif_Sent extends AppCompatActivity {

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;
    Context mContext;

    RecyclerView listview;
    ListAdapter mListadapter;
    FloatingActionButton fabnotif_add;
    public static Activity Form_Notif_Sent;

    int [] no_list,readstatus_list;
    String [] dari_list,isi_list,ke_list,tanggal_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_notif_sent);
        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;
        Form_Notif_Sent = this;

        fabnotif_add = (FloatingActionButton) findViewById(R.id.fabnotif_add);
        listview = (RecyclerView)findViewById(R.id.form_user_notif_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(layoutManager);

        checkUserType();

        fabnotif_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectChoice();
            }
        });

    }


    private void SelectChoice(){

        final CharSequence[] items={"Pesan Diterima","Ke Mahasiswa","Ke Semua Mahasiswa", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Form_Notif_Sent.this);
        builder.setTitle("Pilih jenis penerima notifikasi");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Ke Mahasiswa")) {

                    sendSingle();

                } else if (items[i].equals("Ke Semua Mahasiswa")) {

                    sendAll();

                } else if (items[i].equals("Pesan Diterima")) {

                    AllNotif();

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }

    public void sendSingle() {
        Intent intent = new Intent(this, Form_Dosen_Notif_Single.class);
        startActivity(intent);
    }

    public void sendAll() {
        Intent intent = new Intent(this, Form_Dosen_Notif_All.class);
        startActivity(intent);
    }

    public void AllNotif() {
        Intent intent = new Intent(this, Form_User_Notif.class);
        finish();
        startActivity(intent);
    }

    public void initListView(int UserId){
        Call<SentNotifResponse> getSentNotif = mApiService.getSentNotif(
                UserId
        );
        getSentNotif.enqueue(new Callback<SentNotifResponse>() {
            @Override
            public void onResponse(Call<SentNotifResponse> call, Response<SentNotifResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<NotifSent> list = new ArrayList<>();
                    list = response.body().getNotifSent();
                    no_list = new int[list.size()];
                    dari_list = new String[list.size()];
                    isi_list = new String[list.size()];
                    ke_list = new String[list.size()];
                    tanggal_list =  new String[list.size()];
                    for (int i =0;i<list.size();i++) {
                        no_list[i] = list.get(i).getNo();
                        dari_list[i] = String.valueOf(list.get(i).getDari());
                        isi_list[i] = list.get(i).getIsi();
                        ke_list[i]  = String.valueOf(list.get(i).getKe());
                        tanggal_list[i] = list.get(i).getTanggal();
                    }
                    ArrayList data = new ArrayList<DataNote>();
                    for (int i = 0; i < list.size(); i++)
                    {
                        data.add(
                                new DataNote
                                        (
                                                no_list[i],
                                                dari_list[i],
                                                isi_list[i],
                                                ke_list[i],
                                                tanggal_list[i]
                                        ));
                    }

                    mListadapter = new ListAdapter(data);
                    listview.setAdapter(mListadapter);
                }
            }
            @Override
            public void onFailure(Call<SentNotifResponse> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Jaringan Bermasalah", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }


    public void checkUserType(){
        if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
            initListView(mPrefs.getUserID());
            fabnotif_add.hide();
        } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            initListView(mPrefs.getUserID());
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
            TextView textViewDari;
            TextView textViewPesan;
            TextView textViewTanggal;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewDari = (TextView) itemView.findViewById(R.id.TVNotif_cardview_dari);
                this.textViewPesan = (TextView) itemView.findViewById(R.id.TVNotif_cardview_pesan);
                this.textViewTanggal = (TextView) itemView.findViewById(R.id.TVNotif_cardview_tanggal);

            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_user_notif_cardview, parent, false);

            ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.textViewDari.setText("Ke : "+dataList.get(position).getKe());
            holder.textViewPesan.setText(dataList.get(position).getIsi());
            holder.textViewTanggal.setText(dataList.get(position).getTanggal());
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new AlertDialog.Builder(mContext)
                            .setTitle(dataList.get(position).getKe())
                            .setMessage(dataList.get(position).getIsi())

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton("Close", null)
                            .setIcon(R.drawable.ic_notifications_black_24dp)
                            .show();
                    holder.textViewDari.setTypeface(Typeface.DEFAULT);
                    holder.textViewPesan.setTypeface(Typeface.DEFAULT);
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
