package com.hula.myapplication.view.message;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hula.myapplication.R;
import com.hula.myapplication.adapter.PagerAdapter;
import com.hula.myapplication.view.message.fragment.BuddyFragment;
import com.hula.myapplication.view.message.fragment.GroupFragment;
import com.hula.myapplication.view.search.SearchFragment;
import com.hula.myapplication.widget.NoScrollViewPager;

import java.util.ArrayList;

import tim.com.libnetwork.base.BaseTransFragment;

public class MessageFragment extends BaseTransFragment {
    public static final String TAG = MessageFragment.class.getSimpleName();
    private TextView[] tvTabs;
    private TextView tvBuddies;
    private TextView tvGroups;
    private NoScrollViewPager viewpager;

    private PagerAdapter adapter;
    private BuddyFragment subFragment1;
    private GroupFragment subFragment2;
    private ArrayList<String> tabs = new ArrayList<>();
    public int showPosition = 0;

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
        viewpager = rootView.findViewById(R.id.viewpager_invite);
        tvBuddies = rootView.findViewById(R.id.tv_buddies);
        tvGroups = rootView.findViewById(R.id.tv_groups);
        tvBuddies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTab(0);
            }
        });
        tvGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTab(1);
            }
        });
        tvTabs = new TextView[]{tvBuddies, tvGroups};
        setView();
        showTab(showPosition);
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

    private void setView() {
        tabs.clear();
        fragments.clear();
        for (TextView textView : tvTabs) {
            tabs.add("");
        }
        fragments.add(subFragment1 = BuddyFragment.getInstance());
        fragments.add(subFragment2 = GroupFragment.getInstance());
        if (adapter == null) {
            adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), fragments, tabs);
            viewpager.setAdapter(adapter);
            viewpager.setOffscreenPageLimit(fragments.size() - 1);
            viewpager.setCurrentItem(0);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public void showTab(int position) {
        if (tvTabs == null) {
            return;
        }
        for (int i = 0; i < tvTabs.length; i++) {
            if (i == position) {
                tvTabs[i].setSelected(true);
            } else {
                tvTabs[i].setSelected(false);
            }
        }
        viewpager.setCurrentItem(position);
    }
}
