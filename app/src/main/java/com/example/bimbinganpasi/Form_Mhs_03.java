package com.example.bimbinganpasi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bimbinganpasi.Data.ListForm4Response;
import com.example.bimbinganpasi.Data.List_Form4;
import com.example.bimbinganpasi.Data.MessageResponse;
import com.example.bimbinganpasi.Data.Porto_Mhs;
import com.example.bimbinganpasi.Data.PortofolioMhsResponse;
import com.example.bimbinganpasi.Form_03.DataNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Mhs_03 extends AppCompatActivity {

    BaseAPIService mApiService;
    PreferencesHelper mPrefs;
    Context mContext;
    public static Activity Form_Mhs_03;

    RecyclerView listview;
    ListAdapter mListadapter;
    FloatingActionButton fab_add;

    int [] no_list;
    String [] kegiatan_list,keterangan_list,kategori_list,semester_list,komentar_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_03);

        Form_Mhs_03 = this;

        mApiService = UtilsApi.getClient().create(BaseAPIService.class); // meng-init yang ada di package apihelper
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;

        listview = (RecyclerView)findViewById(R.id.form_mhs_03_list);
        fab_add = (FloatingActionButton) findViewById(R.id.fab3_add);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(layoutManager);

        checkUserType();

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),Form_Mhs_03_tambah.class);
                startActivity(i);
            }
        });

    }

    public void initListView(String UserId){
        Call<PortofolioMhsResponse> getPortoMhs = mApiService.getPortoMhs(
                UserId
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
                    komentar_list = new String[list.size()];
                    for (int i =0;i<list.size();i++) {
                        no_list[i] = list.get(i).getNo();
                        kegiatan_list[i] = list.get(i).getKegiatan();
                        keterangan_list[i] = list.get(i).getKeterangan();
                        kategori_list[i] = list.get(i).getKategori();
                        semester_list[i]  = list.get(i).getSemester();
                        komentar_list[i] = list.get(i).getKomentar();
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
                                                semester_list[i],
                                                komentar_list[i]
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

    public void checkUserType(){
        if (mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
            initListView(String.valueOf(mPrefs.getUserID()));

        } else if (mPrefs.getUserType().equalsIgnoreCase("dosen")){
            initListView(String.valueOf(mPrefs.getSelectedUserId()));
            fab_add.hide();
        }
    }

    public void deletePortofolio(int no_portofolio){
        Call<MessageResponse> deletePortofolio = mApiService.deletePortofolio(
                no_portofolio
        );
        deletePortofolio.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Portofolio telah dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(getBaseContext(),Form_Mhs_03.class);
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
            TextView textViewKomentar;
            ImageView imageViewDelete;
            Button buttonKomen;
            ViewGroup btn_kmn;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewSemester = (TextView) itemView.findViewById(R.id.TV03_cardview_semester);
                this.textViewKegiatan = (TextView) itemView.findViewById(R.id.TV03_cardview_kegiatan);
                this.textViewKeterangan = (TextView) itemView.findViewById(R.id.TV03_cardview_keterangan);
                this.textViewKategori = (TextView) itemView.findViewById(R.id.TV03_cardview_kategori);
                this.textViewKomentar = (TextView) itemView.findViewById(R.id.TV03_cardview_komentar);
                this.imageViewDelete = (ImageView) itemView.findViewById(R.id.IV03_cardview_delete);
                this.buttonKomen = (Button) itemView.findViewById(R.id.TV03_BtnKomen);
                btn_kmn = (ViewGroup) buttonKomen.getParent();
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
            if(mPrefs.getUserType().equalsIgnoreCase("mahasiswa")){
                holder.btn_kmn.removeView(holder.buttonKomen);
            } else {
                holder.imageViewDelete.setVisibility(View.GONE);
            }
            holder.textViewKegiatan.setText(dataList.get(position).getKegiatan());
            holder.textViewKeterangan.setText(dataList.get(position).getKeterangan());
            holder.textViewKategori.setText(dataList.get(position).getKategori());
            holder.textViewSemester.setText(dataList.get(position).getSemester());
            if (!dataList.get(position).getKomentar().equals("")) {
                holder.textViewKomentar.setText(dataList.get(position).getKomentar());
            } else {

            }
            holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Delete Portofolio")
                            .setMessage("Are you sure you want to delete this portofolio?")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                    deletePortofolio(dataList.get(position).getNo());
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
            holder.buttonKomen.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(getBaseContext(), Form_Mhs_03_tambahkomentar.class);
                    intent.putExtra("no_porto", dataList.get(position).getNo());
                    startActivity(intent);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Komentar")
                            .setMessage(dataList.get(position).getKomentar())

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton("Close", null)
                            .setIcon(R.drawable.ic_speaker_notes_black_24dp)
                            .show();
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
