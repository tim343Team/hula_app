package com.hula.myapplication.view.home.adapter.home;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.widget.HulaPartyItemLayout;
import com.hula.myapplication.R;
import com.hula.myapplication.app.RootApplication;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.dao.home.EventsItem;
import com.hula.myapplication.util.HUtilScreen;
import com.hula.myapplication.view.home.EventsDetailActivity;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.List;
import java.util.Objects;

public class MultiPartyViewData extends AbsMultiItemViewData {
    private final DataItemDao data;
    private final int w;
    private final int h;
    private final boolean showFindBuddy;
    private final boolean showPersion;
    private final String title;

    public MultiPartyViewData(DataItemDao data, String title, int wdp, int hdP, boolean showFindBuddy, boolean showPersion) {
        super(R.layout.home_item_multi_party_view);
        this.title = title;
        this.data = data;
        this.w = HUtilScreen.dp2px(RootApplication.getContext(), wdp);
        this.h = HUtilScreen.dp2px(RootApplication.getContext(), hdP);
        this.showFindBuddy = showFindBuddy;
        this.showPersion = showPersion;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void convert(BaseViewHolder grouphelper) {
        grouphelper.setText(R.id.tv_title, title);
        View view = grouphelper.getView(R.id.tv_title);
        Object tag = view.getTag();

        RecyclerView recyclerView = grouphelper.getView(R.id.recyclerView);
        if (recyclerView.getAdapter() == null || !title.equals(tag) ||  recyclerView.getAdapter().getItemCount() != data.getEvents().size()) {
            BaseQuickAdapter<EventsItem, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<EventsItem, BaseViewHolder>(R.layout.item_event_multi_party_view) {
                @Override
                protected void convert(BaseViewHolder helper, EventsItem item) {
                    View itemView = helper.itemView;
                    ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
                    if (layoutParams != null && layoutParams.width != w) {
                        layoutParams.width = w;
                        layoutParams.height = h;
                        itemView.setLayoutParams(layoutParams);
                    }
                    HulaPartyItemLayout view = helper.getView(R.id.partylayout);
                    view.setShow(showFindBuddy, showPersion);
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
        view.setTag(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultiPartyViewData)) return false;
        MultiPartyViewData that = (MultiPartyViewData) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    public void add(List<EventsItem> next) {
        data.getEvents().addAll(next);
    }
}
