package com.hula.myapplication.view.message.fragment;

import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.databinding.FragmentGroupBinding;

import tim.com.libnetwork.base.BaseLazyFragment;

public class GroupFragment extends BaseLazyFragment {
    private FragmentGroupBinding binding;

    public static GroupFragment getInstance() {
        GroupFragment fragment = new GroupFragment();
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
        binding = FragmentGroupBinding.inflate(getLayoutInflater());
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
