package com.hula.myapplication.view.home.adapter;

import android.view.View;

import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;

import tim.com.libnetwork.utils.ScreenUtils;

public class FeaturedEventViewData extends AbsMultiItemViewData {
    private final Object data;

    public FeaturedEventViewData(Object data) {
        super(R.layout.home_item_featured_event);
        this.data = data;
    }

    @Override
    public void convert(BaseViewHolder helper) {
        ViewPager2 viewPager2 = helper.getView(R.id.viewpager);
        if (viewPager2.getAdapter() == null) {
            BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<Object, BaseViewHolder>(R.layout.item_featured_event) {
                @Override
                protected void convert(BaseViewHolder helper, Object item) {

                }
            };
            baseQuickAdapter.setNewData(new ArrayList<Object>() {
                {
                    add(new Object());
                    add(new Object());
                    add(new Object());
                    add(new Object());
                    add(new Object());
                    add(new Object());
                    add(new Object());
                    add(new Object());

                }
            });
            baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
            viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            viewPager2.setAdapter(baseQuickAdapter);
            viewPager2.setPageTransformer(new MarginPageTransformer(ScreenUtils.dip2px(viewPager2.getContext(), 8F)));
        }
    }
}
