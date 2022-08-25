package com.hula.myapplication.view.home.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

abstract class AbsMultiItemViewData {
    private final int layoutID;

    public AbsMultiItemViewData(int layoutID) {
        this.layoutID = layoutID;
    }


    abstract void convert(BaseViewHolder helper);

    public final int getItemType() {
        return layoutID;
    }
}
