package com.hula.myapplication.widget.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

public abstract class AbsMultiItemViewData {
    private final int layoutID;

    public AbsMultiItemViewData(int layoutID) {
        this.layoutID = layoutID;
    }


    public abstract void convert(BaseViewHolder helper);

    public final int getItemType() {
        return layoutID;
    }
}
