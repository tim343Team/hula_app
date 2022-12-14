package com.hula.myapplication.app;

import android.content.Context;

import tim.com.libnetwork.app.MyApplication;

public class RootApplication extends MyApplication {
    private static RootApplication instance;
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取程序的Application对象
     */
    public static RootApplication getApp() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initApplication();
        HulaInit.init();
        /*二维码识别*///
//        ZXingLibrary.initDisplayOpinion(this);
    }


    private void initApplication() {
        instance = this;
        ActivityLifecycleManager.init(this);
    }

    public static RootApplication getInstance() {
        return instance;
    }
}
