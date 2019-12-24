package com.example.bimbinganpasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bimbinganpasi.Data.Mahasiswa;
import com.example.bimbinganpasi.Data.MhsBimbinganResponse;
import com.example.bimbinganpasi.Data.Porto_Mhs;
import com.example.bimbinganpasi.Data.PortofolioMhsResponse;
import com.example.bimbinganpasi.Data.UserDataResponse;
import com.example.bimbinganpasi.Form_Menu.Adapter.DataNote;;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form_Dosen_Menu extends AppCompatActivity {
    private TextView TV_nama,TV_no_induk;
    private RecyclerView listview;
    PreferencesHelper mPrefs;
    BaseAPIService mApiService;
    Context mContext;
    ListAdapter mListadapter;

    int [] no_id,semester;
    String [] nama,nim,kat_sks,kat_ipk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_dosen_menu);

        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        mPrefs = ((BimbPA) getApplication()).getPrefs();
        mContext = this;

        TV_nama = (TextView) findViewById(R.id.NamaDosen);
        TV_no_induk = (TextView) findViewById(R.id.NIDosen);
        listview = (RecyclerView) findViewById(R.id.form_dosen_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(layoutManager);

        initListView();
    }

    @Override
    public void onResume(){
        super.onResume();
        mApiService = UtilsApi.getClient().create(BaseAPIService.class);
        requestData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbars, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.exit:
            //add the function to perform here
            mPrefs.setUserIsSignIn(false);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return(true);
        case R.id.chgpassword:
            Intent intent2 = new Intent(this, Form_Change_Password.class);
            startActivity(intent2);
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    public void requestData(){
        Call<UserDataResponse> loggedinuserrequestdosen = mApiService.loggedinuserrequestdosen(
                String.valueOf(mPrefs.getUserID()));
        loggedinuserrequestdosen.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                String iserror_ = response.body().getIserror();
                if (iserror_.equals("false")) {
                    // Jika login berhasil maka data nama yang ada di response API
                    // akan diparsing ke activity selanjutnya.
                    String nama = response.body().getNama();
                    String no_induk = response.body().getNomor_induk();
                    TV_nama.setText(nama);
                    TV_no_induk.setText(no_induk);
                }
            }
            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    public void initListView(){
        Call<MhsBimbinganResponse> mhsBimbinganRequest = mApiService.mhsBimbinganRequest(
                String.valueOf(mPrefs.getUserID())
        );
        mhsBimbinganRequest.enqueue(new Callback<MhsBimbinganResponse>() {
            @Override
            public void onResponse(Call<MhsBimbinganResponse> call, Response<MhsBimbinganResponse> response) {
                boolean iserror_ = response.body().getError();
                if (iserror_ == false) {
                    List<Mahasiswa> list = new ArrayList<>();
                    list = response.body().getMahasiswa();
                    no_id = new int[list.size()];
                    nim = new String[list.size()];
                    nama = new String[list.size()];
                    semester = new int[list.size()];
                    kat_sks = new String[list.size()];
                    kat_ipk = new String[list.size()];
                    for (int i =0;i<list.size();i++) {
                        no_id[i] = list.get(i).getNoId();
                        nim[i] = list.get(i).getNimMhs();
                        nama[i] = list.get(i).getNamaMhs();
                        semester[i] = list.get(i).getSemester();
                        kat_sks[i] = list.get(i).getKategoriSks();
                        kat_ipk[i]  = list.get(i).getKategoriIpk();
                    }

                    ArrayList data = new ArrayList<DataNote>();
                    for (int i = 0; i < list.size(); i++)
                    {
                        data.add(
                                new DataNote
                                        (
                                                nim[i],
                                                nama[i],
                                                semester[i],
                                                kat_sks[i],
                                                kat_ipk[i]
                                        ));
                    }

                    mListadapter = new ListAdapter(data);
                    listview.setAdapter(mListadapter);

                }
            }
            @Override
            public void onFailure(Call<MhsBimbinganResponse> call, Throwable t) {
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
            TextView textViewNIM;
            TextView textViewNama;
            TextView textViewSemester;
            TextView textViewkatSKS;
            TextView textViewKatIPK;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewSemester = (TextView) itemView.findViewById(R.id.TVD_cardview_semester);
                this.textViewNIM = (TextView) itemView.findViewById(R.id.TVD_cardview_nim);
                this.textViewNama = (TextView) itemView.findViewById(R.id.TVD_cardview_nama);
                this.textViewkatSKS = (TextView) itemView.findViewById(R.id.TVD_cardview_katsks);
                this.textViewKatIPK = (TextView) itemView.findViewById(R.id.TVD_cardview_katipk);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_dosen_menu_cardview, parent, false);

            ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.textViewNIM.setText(dataList.get(position).getNim());
            holder.textViewNama.setText(dataList.get(position).getNama());
            holder.textViewSemester.setText(String.valueOf(dataList.get(position).getSemester()));
            holder.textViewkatSKS.setText(dataList.get(position).getKatsks());
            holder.textViewKatIPK.setText(dataList.get(position).getKatipk());

            if(holder.textViewkatSKS.getText().equals("kritis") || holder.textViewkatSKS.getText().equals("kurang")){
                holder.textViewkatSKS.setTextColor(Color.rgb(255,0,0));
            }
            if(holder.textViewKatIPK.getText().equals("kritis") || holder.textViewKatIPK.getText().equals("kurang")){
                holder.textViewKatIPK.setTextColor(Color.rgb(255,0,0));
            }

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mPrefs.setSelectedUserId(no_id[position]);
                    Intent intent = new Intent(mContext, Form_Mhs_Menu.class);
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
