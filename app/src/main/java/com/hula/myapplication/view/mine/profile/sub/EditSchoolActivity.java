package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.databinding.ActivitySettingNameBinding;
import com.hula.myapplication.databinding.ActivitySettingSchoolBinding;

import tim.com.libnetwork.base.BaseActivity;

public class EditSchoolActivity extends BaseActivity {
    private ActivitySettingSchoolBinding binding;
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, EditSchoolActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivitySettingSchoolBinding.inflate(getLayoutInflater());
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
        binding.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectSchoolActivity.actionStart(EditSchoolActivity.this);
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
