package com.hula.myapplication.view.home.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.HulaPartyItemLayout;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.dao.home.EventsItem;
import com.hula.myapplication.util.BusinessUtils;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.view.home.EventsDetailActivity;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;
import java.util.Objects;

public class JustForYouPartyItemViewData extends AbsMultiItemViewData {
    private final String title;
    private final DataItemDao data;

    public JustForYouPartyItemViewData(DataItemDao data) {
        super(R.layout.home_item_just_for_you);
        this.data = data;
        this.title = "Just For You";
    }

    @Override
    public void convert(BaseViewHolder grouphelper) {
        grouphelper.setText(R.id.tv_title, title);
        RecyclerView recyclerView = grouphelper.getView(R.id.recyclerView);
        if (recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() != data.getEvents().size()) {
            BaseQuickAdapter<EventsItem, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<EventsItem, BaseViewHolder>(R.layout.item_just_for_you) {
                @Override
                protected void convert(BaseViewHolder helper, EventsItem item) {
                    HulaPartyItemLayout view = helper.getView(R.id.partylayout);
                    view.setData(item);
                }
            };
            baseQuickAdapter.setNewData(data.getEvents());
            baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(view.getContext(), EventsDetailActivity.class);
                    intent.putExtra("id", data.getEvents().get(position).getId());
                    view.getContext().startActivity(intent);
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(grouphelper.itemView.getContext(), RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(baseQuickAdapter);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JustForYouPartyItemViewData)) return false;
        JustForYouPartyItemViewData that = (JustForYouPartyItemViewData) o;
        return Objects.equals(title, that.title) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, data);
    }
}
