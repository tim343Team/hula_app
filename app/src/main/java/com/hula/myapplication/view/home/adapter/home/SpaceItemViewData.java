package com.hula.myapplication.view.home.adapter.home;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.Objects;

public class SpaceItemViewData extends AbsMultiItemViewData {
    private final int height;

    public SpaceItemViewData(int height) {
        super(R.layout.item_space);
        this.height = height;
    }

    @Override
    public void convert(BaseViewHolder helper) {
        ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
        layoutParams.height = height;
        helper.itemView.setLayoutParams(layoutParams);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpaceItemViewData)) return false;
        SpaceItemViewData that = (SpaceItemViewData) o;
        return height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height);
    }
}
