package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hula.myapplication.databinding.ActivityNewBinding;
import com.hula.myapplication.databinding.ActivitySettingNameBinding;
import com.hula.myapplication.view.mine.what_new.NewActivity;

import tim.com.libnetwork.base.BaseActivity;

public class EditNameActivity extends BaseActivity {
    private ActivitySettingNameBinding binding;
    private EditText editName;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, EditNameActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivitySettingNameBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        editName = binding.editName;
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
