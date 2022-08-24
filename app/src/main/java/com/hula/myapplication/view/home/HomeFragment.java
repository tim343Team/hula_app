package com.hula.myapplication.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hula.myapplication.R;
import com.hula.myapplication.view.home.adapter.FeaturedEventViewData;
import com.hula.myapplication.view.home.adapter.GroupsYouMightLikeViewData;
import com.hula.myapplication.view.home.adapter.HomeAdapter;
import com.hula.myapplication.view.home.adapter.JustForYouViewData;
import com.hula.myapplication.view.home.adapter.RecommendedBuddiesViewData;
import com.hula.myapplication.view.login.RegisterActivity;
import com.hula.myapplication.widget.HuLaActionBar;
import com.hula.myapplication.widget.htoast.ToastUtil;

import tim.com.libnetwork.base.BaseTransFragment;

public class HomeFragment extends BaseTransFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private HomeAdapter homeAdapter = new HomeAdapter();

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
        homeAdapter = new HomeAdapter();
        homeAdapter.addData(new RecommendedBuddiesViewData(new Object()));
        homeAdapter.addData(new JustForYouViewData(new Object()));
        homeAdapter.addData(new FeaturedEventViewData(new Object()));
        homeAdapter.addData(new GroupsYouMightLikeViewData(new Object()));
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(homeAdapter);

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
