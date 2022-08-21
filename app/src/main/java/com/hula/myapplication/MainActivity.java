package com.hula.myapplication;

import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.sp.SharedPrefsHelper;
import com.hula.myapplication.view.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

import tim.com.libnetwork.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected View getActivityLayoutView() {
        return null;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (!isTaskRoot()) {
            finish();
            return;
        }
        Timer timer = new Timer();
        timer.schedule(new MyTask(),2000);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }


    class MyTask extends TimerTask {
        @Override
        public void run() {
            if (SharedPrefsHelper.getInstance().getFirst()) {
                //第一次进入应用
                SharedPrefsHelper.getInstance().saveFirst();
            }else {
                if(SharedPrefsHelper.getInstance().getToken().isEmpty()){
                    //未登录
                }else {
                    //登录
                }
            }
            HomeActivity.actionStart(MainActivity.this);
            finish();
        }
    }
}
