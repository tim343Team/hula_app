package com.hula.myapplication.view.message.fragment;

import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.databinding.FragmentBuddyBinding;

import tim.com.libnetwork.base.BaseLazyFragment;

public class BuddyFragment extends BaseLazyFragment {
    private FragmentBuddyBinding binding;

    public static BuddyFragment getInstance() {
        BuddyFragment fragment = new BuddyFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected View getLayoutView() {
        binding = FragmentBuddyBinding.inflate(getLayoutInflater());
        return binding.getRoot();
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
