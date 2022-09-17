package com.hula.myapplication.view.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.hula.myapplication.R;
import com.hula.myapplication.databinding.ActivityRegisterNotificationBinding;
import com.hula.myapplication.view.HomeActivity;
import com.hula.myapplication.widget.dialog.PermissonDialog;

import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class RegisterNotificationActivity extends BaseActivity {
    private ActivityRegisterNotificationBinding binding;

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityRegisterNotificationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    private void startNextPage() {
        if (!PermissionUtils.isGranted(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Intent intent = new Intent(this, RegisterLocationActivity.class);
            startActivity(intent);
            return;
        }
        HomeActivity.start(this);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViews(Bundle savedInstanceState) {
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextPage();
            }
        });
        binding.imageSwitch.setChecked(NotificationUtils.areNotificationsEnabled());
        binding.imageSwitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (binding.imageSwitch.isChecked()) {
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    new PermissonDialog.Builder()
                            .setDrawableId(R.mipmap.icon_notification)
                            .setTitle("Get notified!")
                            .setSubTitle("Get notified about trending events, new buddy invites, and find new friends!")
                            .request(getSupportFragmentManager(), new PermissonDialog.PermissionHand() {
                                @Override
                                public boolean isGranted(String[] permissions) {
                                    return NotificationUtils.areNotificationsEnabled();
                                }

                                @Override
                                public void request(FragmentActivity activity, String[] permissions) {
                                    // Links to this app's notification settings.
                                    Intent intent = new Intent();
                                    intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                    intent.putExtra("app_package", activity.getPackageName());
                                    intent.putExtra("app_uid", activity.getApplicationInfo().uid);
                                    // for Android 8 and above
                                    intent.putExtra("android.provider.extra.APP_PACKAGE", activity.getPackageName());
                                    activity.startActivity(intent);
                                }

                                @Override
                                public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                                }
                            });
                }
                return true;
            }
        });
    }
    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.imageSwitch.setChecked(NotificationUtils.areNotificationsEnabled());
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
