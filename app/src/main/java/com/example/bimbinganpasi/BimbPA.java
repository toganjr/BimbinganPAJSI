package com.example.bimbinganpasi;

import android.app.Application;

public class BimbPA extends Application {
    private PreferencesHelper mPrefs;

    @Override
    public void onCreate() {
        super.onCreate();
        mPrefs = new PreferencesHelper(getApplicationContext());
    }

    public PreferencesHelper getPrefs() {
        return mPrefs;
    }
}