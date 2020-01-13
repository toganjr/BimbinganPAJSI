package com.example.bimbinganpasi;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.bimbinganpasi.Form_04.tabviewadapter.form_mhs_04_SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Form_Mhs_04 extends AppCompatActivity {

    private TabLayout tabs;
    public static Activity Form_Mhs_04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs_04);
        form_mhs_04_SectionsPagerAdapter sectionsPagerAdapter = new form_mhs_04_SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Form_Mhs_04 = this;

    }}