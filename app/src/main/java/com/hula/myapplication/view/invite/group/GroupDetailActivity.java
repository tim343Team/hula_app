package com.hula.myapplication.view.invite.group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.databinding.ActivityGroupDetailBinding;
import com.hula.myapplication.databinding.ActivityHelpBinding;
import com.hula.myapplication.view.mine.help.HelpActivity;

import tim.com.libnetwork.base.BaseActivity;

public class GroupDetailActivity extends BaseActivity {
    private ActivityGroupDetailBinding binding;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, GroupDetailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityGroupDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

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
