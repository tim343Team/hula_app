package com.hula.myapplication.view.home.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

abstract class AbsMultiItemViewData<T> {
    private final int layoutID;
    protected final T data;

    public AbsMultiItemViewData(int layoutID, T data) {
        this.layoutID = layoutID;
        this.data = data;
    }


    abstract void convert(BaseViewHolder helper);

    public final int getItemType() {
        return layoutID;
    }
}
