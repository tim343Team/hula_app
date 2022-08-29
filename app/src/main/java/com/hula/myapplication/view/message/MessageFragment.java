package com.hula.myapplication.view.message;

import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.R;
import com.hula.myapplication.view.search.SearchFragment;

import tim.com.libnetwork.base.BaseTransFragment;

public class MessageFragment extends BaseTransFragment {
    public static final String TAG = MessageFragment.class.getSimpleName();

    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void init() {

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

    @Override
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }
}
