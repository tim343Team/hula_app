package com.hula.myapplication.view.mine.preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.databinding.ActivityPreferenceBinding;

import tim.com.libnetwork.base.BaseActivity;

public class PreferenceActivity extends BaseActivity {
    private ActivityPreferenceBinding binding;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, PreferenceActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityPreferenceBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.llAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgeActivity.actionStart(PreferenceActivity.this);
            }
        });
        binding.llPronouns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PronounsActivity.actionStart(PreferenceActivity.this);
            }
        });
        binding.llDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DistanceActivity.actionStart(PreferenceActivity.this);
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
