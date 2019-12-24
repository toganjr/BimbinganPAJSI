package com.example.bimbinganpasi;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.bimbinganpasi.Form_02.tabviewadapter.form_mhs_02_SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Form_Mhs_02 extends AppCompatActivity {

    private TabLayout tabs;
    private TextView catatan;
    public static Activity Form_Mhs_02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_02);

        Form_Mhs_02 = this;

        form_mhs_02_SectionsPagerAdapter sectionsPagerAdapter = new form_mhs_02_SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

}