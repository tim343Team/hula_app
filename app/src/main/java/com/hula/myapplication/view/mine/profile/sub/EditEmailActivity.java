package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hula.myapplication.databinding.ActivitySettingEmailBinding;

import tim.com.libnetwork.base.BaseActivity;

public class EditEmailActivity extends BaseActivity {
    private ActivitySettingEmailBinding binding;
    private EditText editEmail;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, EditEmailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivitySettingEmailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        editEmail = binding.editEmail;
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvDone.setOnClickListener(new View.OnClickListener() {
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
