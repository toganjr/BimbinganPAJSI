package com.example.bimbinganpasi;


import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;

public class PreferencesHelper {
    private SharedPreferences mPrefs;

    private static final String PREFERENCES_NAME = "PREFERENCES_BimbinganPA";
    private static final String PREFERENCES_KEY_USER_IS_SIGN_IN = "PREFERENCES_KEY_USER_IS_SIGN_IN";
    private static final String PREFERENCES_KEY_USER_ID = "PREFERENCES_KEY_USER_ID";
    private static final String PREFERENCES_KEY_SELECTED_USER_ID = "PREFERENCES_KEY_USER_ID";
    private static final String PREFERENCES_KEY_USER_NAME = "PREFERENCES_KEY_USER_NAME";
    private static final String PREFERENCES_KEY_USER_NO = "PREFERENCES_KEY_USER_NO";
    private static final String PREFERENCES_KEY_USER_SEMESTER = "PREFERENCES_KEY_USER_SEMESTER";
    private static final String PREFERENCES_KEY_USER_TYPE = "PREFERENCES_KEY_USER_TYPE";

    public PreferencesHelper(Context context) {
        this.mPrefs = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setUserID(Integer userID){
        mPrefs.edit().putInt(PREFERENCES_KEY_USER_ID,  userID).apply();
    }

    public Integer getUserID(){
        return mPrefs.getInt(PREFERENCES_KEY_USER_ID, 0);
    }

    public void setSelectedUserId(Integer selectedUserId){
        mPrefs.edit().putInt(PREFERENCES_KEY_SELECTED_USER_ID,  selectedUserId).apply();
    }

    public Integer getSelectedUserId(){
        return mPrefs.getInt(PREFERENCES_KEY_SELECTED_USER_ID, 0);
    }

    public void setUserName(String userName){
        mPrefs.edit().putString(PREFERENCES_KEY_USER_NAME,  userName).apply();
    }

    public String getUserName(){
        return mPrefs.getString(PREFERENCES_KEY_USER_NAME,"");
    }

    public void setUserNo(String userNo){
        mPrefs.edit().putString(PREFERENCES_KEY_USER_NO,  userNo).apply();
    }

    public String getUserNo(){
        return mPrefs.getString(PREFERENCES_KEY_USER_NO, "");
    }

    public void setUserSmt(String userSmt){
        mPrefs.edit().putString(PREFERENCES_KEY_USER_SEMESTER,  userSmt).apply();
    }

    public String getUserSmt(){
        return mPrefs.getString(PREFERENCES_KEY_USER_SEMESTER, "");
    }

    public void setUserType(String userType){
        mPrefs.edit().putString(PREFERENCES_KEY_USER_TYPE,  userType).apply();
    }

    public String getUserType(){
        return mPrefs.getString(PREFERENCES_KEY_USER_TYPE, "");
    }

    public void setUserIsSignIn(boolean signIn){
        mPrefs.edit().putBoolean(PREFERENCES_KEY_USER_IS_SIGN_IN, signIn).apply();
    }

    public boolean getUserisSignIn(){
        return mPrefs.getBoolean(PREFERENCES_KEY_USER_IS_SIGN_IN, false);
    }
}