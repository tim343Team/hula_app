package com.hula.myapplication.view.home.adapter;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;

public class GroupsYouMightLikeViewData extends AbsMultiItemViewData {
    private final Object data;

    public GroupsYouMightLikeViewData(Object data) {
        super(R.layout.home_item_groups_you_might_like);
        this.data = data;
    }

    @Override
    public  void convert(BaseViewHolder helper) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        if (recyclerView.getAdapter()==null){
            BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<Object, BaseViewHolder>(R.layout.item_groups_you_might_like) {
                @Override
                protected void convert(BaseViewHolder helper, Object item) {

                }
            };
            baseQuickAdapter.setNewData(new ArrayList<Object>(){
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
