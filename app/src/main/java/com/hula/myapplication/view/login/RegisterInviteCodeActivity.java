package com.hula.myapplication.view.login;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.hula.myapplication.R;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.util.SimTextWatcher;
import com.hula.myapplication.view.HomeActivity;
import com.hula.myapplication.widget.VCEditText;
import com.hula.myapplication.widget.htoast.ToastUtil;

import tim.com.libnetwork.base.BaseActivity;
import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class RegisterInviteCodeActivity extends BaseActivity {

    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }

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
        TextView sendCode = findViewById(R.id.tv_send);
        VCEditText editText = findViewById(R.id.edit_vc);
        editText.addTextChangedListener(new SimTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                sendCode.setEnabled(s.length() == 6);
            }
        });

        View tvNoCode = findViewById(R.id.tv_no_code);
        tvNoCode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                next();
            }
        });
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WonderfulOkhttpUtils.post()
                        .addParams("user_id", HService.getService(ServiceProfile.class).getUserId())
                        .addParams("code",editText.getText().toString())
                        .build()
                        .getCall()
                        .bindLifecycle(RegisterInviteCodeActivity.this)
                        .enqueue(new GsonWalkDogCallBack<RemoteData<Object>>() {
                            @Override
                            protected void onRes(RemoteData<Object> data) throws Exception {
                                ToastUtil.showToast("Join the group success");
                                next();
                            }
                        });
            }
        });
    }

    private void next() {
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
