package com.hula.myapplication.view.mine.sub_fragment;

import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.R;
import com.hula.myapplication.databinding.FragmentMineEditBinding;
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
        return R.layout.fragment_mine_preview;
    }

    @Override
    protected View getLayoutView() {
//        binding = FragmentMinePreviewBinding.inflate(getLayoutInflater());
//        return binding.getRoot();
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
