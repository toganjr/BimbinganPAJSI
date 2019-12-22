package com.example.bimbinganpasi.Form_04.tabviewadapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.bimbinganpasi.Form_04.fragment.form_mhs_04_fragment1;
import com.example.bimbinganpasi.Form_04.fragment.form_mhs_04_fragment2;
import com.example.bimbinganpasi.Form_04.fragment.form_mhs_04_fragment3;
import com.example.bimbinganpasi.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class form_mhs_04_SectionsPagerAdapter extends FragmentPagerAdapter {


    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.form_04_tab_text_1, R.string.form_04_tab_text_2, R.string.form_04_tab_text_3 };
    private final Context mContext;

    public form_mhs_04_SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new form_mhs_04_fragment1();
                break;
            case 1 :
                fragment = new form_mhs_04_fragment2();
                break;
            case 2 :
                fragment = new form_mhs_04_fragment3();
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }


}