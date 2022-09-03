package com.hula.myapplication.view.mine.profile;

import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.databinding.FragmentMinePreviewBinding;

import tim.com.libnetwork.base.BaseLazyFragment;

public class PreviewFragment extends BaseLazyFragment {
    private FragmentMinePreviewBinding binding;

    public static PreviewFragment getInstance() {
        PreviewFragment fragment = new PreviewFragment();
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
        binding = FragmentMinePreviewBinding.inflate(getLayoutInflater());
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
