package com.hula.myapplication.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hula.myapplication.R;
import com.hula.myapplication.util.HUtilScreen;
import com.hula.myapplication.view.home.adapter.CreateYourOwnViewData;
import com.hula.myapplication.view.home.adapter.EventsSubmitByHulaViewData;
import com.hula.myapplication.view.home.adapter.FeaturedEventViewData;
import com.hula.myapplication.view.home.adapter.GroupsYouMightLikeViewData;
import com.hula.myapplication.widget.adapter.MutiAdapter;
import com.hula.myapplication.view.home.adapter.PartyItemViewData;
import com.hula.myapplication.view.home.adapter.RecommendedBuddiesViewData;
import com.hula.myapplication.view.home.adapter.SpaceItemViewData;
import com.hula.myapplication.view.login.RegisterActivity;
import com.hula.myapplication.widget.HuLaActionBar;

import tim.com.libnetwork.base.BaseTransFragment;

public class HomeFragment extends BaseTransFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private MutiAdapter homeAdapter = new MutiAdapter();

    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
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
        HuLaActionBar actionBar = rootView.findViewById(R.id.action_bar);
        actionBar.setMenuClickListener(new HuLaActionBar.OnItemClickListener() {
            @Override
            public void onClick(int position, View view) {
                Intent intent = new Intent(requireActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        homeAdapter = new MutiAdapter();
        homeAdapter.addData(new RecommendedBuddiesViewData(new Object()));
        homeAdapter.addData(new PartyItemViewData("Just For You",new Object()));
        homeAdapter.addData(new FeaturedEventViewData(new Object()));
        homeAdapter.addData(new GroupsYouMightLikeViewData(new Object()));

        homeAdapter.addData(new CreateYourOwnViewData());
        homeAdapter.addData(new PartyItemViewData("You joined the matching pool for... ",new Object()));
        homeAdapter.addData(new EventsSubmitByHulaViewData(new Object()));
        homeAdapter.addData(new PartyItemViewData("College events",new Object()));
        homeAdapter.addData(new PartyItemViewData("Happening soon",new Object()));
        homeAdapter.addData(new PartyItemViewData("Newly posted",new Object()));
        homeAdapter.addData(new PartyItemViewData("Trending events",new Object()));
        homeAdapter.addData(new SpaceItemViewData(HUtilScreen.dp2px(requireActivity(),38)));

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
