package com.hula.myapplication.view.home.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;

public class CreateYourOwnViewData extends AbsMultiItemViewData {

    private boolean showtext = true;

    public CreateYourOwnViewData() {
        super(R.layout.home_item_create_your_own);
    }

    @Override
    void convert(BaseViewHolder helper) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (showtext) {
                    helper.getView(R.id.tv_hint).setVisibility(View.VISIBLE);
                    helper.getView(R.id.iv_down).setRotation(0);
                } else {
                    helper.getView(R.id.tv_hint).setVisibility(View.GONE);
                    helper.getView(R.id.iv_down).setRotation(180);
                }
            }
        };
        runnable.run();
        helper.getView(R.id.iv_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtext = !showtext;
                runnable.run();
            }
        });
    }
}
