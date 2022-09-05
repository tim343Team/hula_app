package com.hula.myapplication.view.home.adapter;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;
import java.util.Objects;

public class RecommendedBuddiesViewData extends AbsMultiItemViewData {
    private final DataItemDao data;

    public RecommendedBuddiesViewData(DataItemDao data) {
        super(R.layout.home_item_recommended);
        this.data = data;
    }

    @Override
    public void convert(BaseViewHolder helper) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        if (recyclerView.getAdapter() == null) {


            BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<Object, BaseViewHolder>(R.layout.item_recommended) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecommendedBuddiesViewData)) return false;
        RecommendedBuddiesViewData that = (RecommendedBuddiesViewData) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
