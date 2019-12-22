package com.example.bimbinganpasi.Form_02.tabviewadapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bimbinganpasi.Form_02.fragment.form_mhs_02_fragment1;
import com.example.bimbinganpasi.Form_02.fragment.form_mhs_02_fragment2;
import com.example.bimbinganpasi.Form_02.fragment.form_mhs_02_fragment3;
import com.example.bimbinganpasi.Form_02.fragment.form_mhs_02_fragment4;
import com.example.bimbinganpasi.Form_02.fragment.form_mhs_02_fragment5;
import com.example.bimbinganpasi.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class form_mhs_02_SectionsPagerAdapter extends FragmentPagerAdapter {


    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.form_02_tab_text_1, R.string.form_02_tab_text_2, R.string.form_02_tab_text_3, R.string.form_02_tab_text_4,R.string.form_02_tab_text_5 };
    private final Context mContext;

    public form_mhs_02_SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new form_mhs_02_fragment1();
                break;
            case 1 :
                fragment = new form_mhs_02_fragment2();
                break;
            case 2 :
                fragment = new form_mhs_02_fragment3();
                break;
            case 3 :
                fragment = new form_mhs_02_fragment4();
                break;
            case 4 :
                fragment = new form_mhs_02_fragment5();
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
        // Show 5 total pages.
        return 5;
    }


}