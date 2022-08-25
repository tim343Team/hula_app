package com.hula.myapplication.view.home.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;

public class SpaceItemViewData extends AbsMultiItemViewData {
    private final int height;

    public SpaceItemViewData(int height) {
        super(R.layout.item_space);
        this.height = height;
    }

    @Override
    void convert(BaseViewHolder helper) {
        ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
        layoutParams.height = height;
        helper.itemView.setLayoutParams(layoutParams);
    }
}
