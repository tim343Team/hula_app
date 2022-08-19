package com.hula.myapplication.sp;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;
import com.hula.myapplication.app.RootApplication;


public class SharedPrefsHelper {
    private static final String SHARED_PREFS_NAME = "qb";

    private static final String SP_KEY_FIRST = "FIRST";
    private static final String USER_TOKEN = "USER_TOKEN";


    private static SharedPrefsHelper instance;
    private static final Gson gson = new Gson();

    private SharedPreferences sharedPreferences;

    public static synchronized SharedPrefsHelper getInstance() {
        if (instance == null) {
            instance = new SharedPrefsHelper();
        }

        return instance;
    }

    private SharedPrefsHelper() {
        instance = this;
        sharedPreferences = RootApplication.getInstance().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存是否第一次进入
     */
    public void saveFirst() {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(SP_KEY_FIRST, false).apply();
    }

    /**
     * 获取是否第一次进入
     */
    public boolean getFirst() {
        return sharedPreferences == null ? true : sharedPreferences.getBoolean(SP_KEY_FIRST, true);
    }

    /**
     * 获取token
     */
    public String getToken() {
        return sharedPreferences == null ? "" : sharedPreferences.getString(USER_TOKEN, "");
    }

    /**
     * 保存token
     */
    public void saveToken(String tokenKey) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putString(USER_TOKEN, tokenKey).apply();
    }
}
