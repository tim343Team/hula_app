package com.hula.myapplication.view.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.PermissionUtils;
import com.hula.myapplication.R;
import com.hula.myapplication.databinding.ActivityRegisterLocationBinding;
import com.hula.myapplication.view.HomeActivity;
import com.hula.myapplication.widget.dialog.PermissonDialog;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class RegisterLocationActivity extends BaseActivity {
    private ActivityRegisterLocationBinding binding;

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityRegisterLocationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViews(Bundle savedInstanceState) {
        binding.imageSwitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (binding.imageSwitch.isChecked()) {
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    new PermissonDialog.Builder()
                            .setDrawableId(R.mipmap.icon_location)
                            .setTitle("Enable location")
                            .setSubTitle("We will recommend events and buddies based on your location!")
                            .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                            .request(getSupportFragmentManager(), new PermissonDialog.Builder.StandardPermissionHand() {
                                @Override
                                public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                                    if (allGranted) {
                                        binding.imageSwitch.setChecked(true);
                                    }
                                }
                            });
                }
                return true;
            }
        });
        binding.imageSwitch.setChecked(PermissionUtils.isGranted(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION));

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.start(RegisterLocationActivity.this);
            }
        });
    }

    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
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
    public void onBackPressed() {

    }
}
