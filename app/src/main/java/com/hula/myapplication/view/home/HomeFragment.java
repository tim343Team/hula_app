package com.hula.myapplication.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.R;
import com.hula.myapplication.view.login.RegisterActivity;
import com.hula.myapplication.widget.HuLaActionBar;
import com.hula.myapplication.widget.htoast.ToastUtil;

import tim.com.libnetwork.base.BaseTransFragment;

public class HomeFragment extends BaseTransFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        HuLaActionBar actionBar = rootView.findViewById(R.id.action_bar);
        actionBar.setMenuClickListener(new HuLaActionBar.OnItemClickListener() {
            @Override
            public void onClick(int position, View view) {
                Intent intent = new Intent(requireActivity(), RegisterActivity.class);
                startActivity(intent);
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

    @Override
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }
}
