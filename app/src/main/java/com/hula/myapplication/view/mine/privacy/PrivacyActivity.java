package com.hula.myapplication.view.mine.privacy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.databinding.ActivityPrivacyBinding;
import com.hula.myapplication.databinding.ActivityPronounsBinding;
import com.hula.myapplication.view.mine.preferences.AgeActivity;

import tim.com.libnetwork.base.BaseActivity;

public class PrivacyActivity extends BaseActivity {
    private ActivityPrivacyBinding binding;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, PrivacyActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityPrivacyBinding.inflate(getLayoutInflater());
        return binding.getRoot();    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
