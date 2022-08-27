package com.hula.myapplication.view.home.adapter;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;

public class PartyItemViewData extends AbsMultiItemViewData {
    private final String title;
    private final Object data;

    public PartyItemViewData(String title, Object data) {
        super(R.layout.home_item_just_for_you);
        this.data = data;
        this.title = title;
    }

    @Override
    public void convert(BaseViewHolder helper) {
        helper.setText(R.id.tv_title, title);
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        if (recyclerView.getAdapter() == null) {
            BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<Object, BaseViewHolder>(R.layout.item_just_for_you) {
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
            recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(), RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(baseQuickAdapter);
        }
    }
}
