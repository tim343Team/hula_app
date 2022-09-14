package com.hula.myapplication.view.mine.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tim.com.libnetwork.base.BaseActivity;

public class ProfileDetailActivity extends BaseActivity {

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ProfileDetailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        return null;
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
