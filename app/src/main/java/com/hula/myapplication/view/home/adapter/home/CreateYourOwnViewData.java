package com.hula.myapplication.view.home.adapter.home;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.view.home.AddNewEventActivity;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.Objects;

public class CreateYourOwnViewData extends AbsMultiItemViewData {

    private boolean showtext = true;

    public CreateYourOwnViewData() {
        super(R.layout.home_item_create_your_own);
    }

    @Override
    public void convert(BaseViewHolder helper) {
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
        helper.getView(R.id.tv_create_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddNewEventActivity.class);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof CreateYourOwnViewData;
    }

    @Override
    public int hashCode() {
        return Objects.hash("CreateYourOwnViewData");
    }
}
