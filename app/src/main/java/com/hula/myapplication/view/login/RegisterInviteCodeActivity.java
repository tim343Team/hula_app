package com.hula.myapplication.view.login;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.hula.myapplication.R;
import com.hula.myapplication.view.HomeActivity;

import tim.com.libnetwork.base.BaseActivity;

public class RegisterInviteCodeActivity extends BaseActivity {
    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_register_invite_code;
    }

    @Override
    protected View getActivityLayoutView() {
        return null;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        View viewById = findViewById(R.id.tv_send);
        View tvNoCode = findViewById(R.id.tv_no_code);
        tvNoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NotificationUtils.areNotificationsEnabled()) {
                    Intent intent = new Intent(RegisterInviteCodeActivity.this, RegisterNotificationActivity.class);
                    RegisterInviteCodeActivity.this.startActivity(intent);
                    return;
                }
                if (!PermissionUtils.isGranted(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Intent intent = new Intent(RegisterInviteCodeActivity.this, RegisterLocationActivity.class);
                    RegisterInviteCodeActivity.this.startActivity(intent);
                    return;
                }
                HomeActivity.start(RegisterInviteCodeActivity.this);
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
}
