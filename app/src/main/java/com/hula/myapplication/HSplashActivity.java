package com.hula.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;

import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.ActivitySplashBinding;
import com.hula.myapplication.view.HomeActivity;
import com.hula.myapplication.view.login.RegisterOrLoginActivity;
import com.hula.myapplication.view.login.StartActivity;
import com.hula.myapplication.widget.HuCallBack1;

import tim.com.libnetwork.base.BaseActivity;

@SuppressLint("CustomSplashScreen")
public class HSplashActivity extends BaseActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable startNext = new Runnable() {
        @Override
        public void run() {
            ServiceProfile service = HService.getService(ServiceProfile.class);
            UserInfoData userInfo = service.getUserInfo();
            if (userInfo == null) {
                StartActivity.start(HSplashActivity.this);
            } else {
                Intent intent = new Intent(HSplashActivity.this, HomeActivity.class);
                startActivity(intent);
            }
            handler.removeCallbacks(this);
        }
    };

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        com.hula.myapplication.databinding.ActivitySplashBinding binding = ActivitySplashBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ServiceProfile service = HService.getService(ServiceProfile.class);
        service.refresh();
        handler.postDelayed(startNext, 3000);
        service.addRefreshListener(this, new HuCallBack1<UserInfoData>() {
            @Override
            public void call(UserInfoData userInfoData) {
                handler.post(startNext);
            }
        });
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return true;
    }
}
